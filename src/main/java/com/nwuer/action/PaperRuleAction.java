package com.nwuer.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.nwuer.entity.Chapter;
import com.nwuer.entity.ExamInfo;
import com.nwuer.entity.ObjectiveAnswer;
import com.nwuer.entity.Paper;
import com.nwuer.entity.PaperRule;
import com.nwuer.entity.SubjectiveAnswer;
import com.nwuer.service.ExamInfoService;
import com.nwuer.service.ObjectiveAnswerService;
import com.nwuer.service.PaperRuleService;
import com.nwuer.service.PaperService;
import com.nwuer.service.SubjectiveAnswerService;
import com.nwuer.utils.ValidateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class PaperRuleAction extends ActionSupport implements ModelDriven<PaperRule> {
	private PaperRule paperRule = new PaperRule();
	@Override
	public PaperRule getModel() {
		return paperRule;
	} //模型驱动获取数据
	String info;
	HttpServletRequest req = ServletActionContext.getRequest();
	
	@Autowired
	private PaperRuleService paperRuleService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private ExamInfoService examInfoService;
	@Autowired
	private ObjectiveAnswerService objectiveAnswerService;
	@Autowired
	private SubjectiveAnswerService subjectiveAnswerService;
	@Autowired
	public ValidateUtil validateUtil;
	
	
	public String add() {
		//验证信息
		String start = req.getParameter("beginTime");
		String end = req.getParameter("endTime");
		//检查数据
		if(paperRule.getP_name().trim().equals("")) {
			req.setAttribute("info", "规则名不能为空!");
			return ERROR;
		}
		if(paperRule.getBlank_score()+paperRule.getJudge_score()+paperRule.getSingle_choice_score()+
				paperRule.getTranslate_score()+paperRule.getSimple_question_score()+paperRule.getCompute_score()
				+paperRule.getMix_score() != paperRule.getFull_score()
				) {
			req.setAttribute("info", "各题型分数和总分不一致!");
			return ERROR;
		}
		//检查章节分布和题目类型时填写是否符合
		int[] cpt = new int[7];
		for(Chapter c : paperRule.getChapter()) {
			cpt[0] += c.getSingle_choice_num();
			cpt[1] += c.getJudge_num();
			cpt[2] += c.getBlank_num();
			cpt[3] += c.getTranslate_num();
			cpt[4] += c.getSimple_question_num();
			cpt[5] += c.getCompute_num();
			cpt[6] += c.getMix_num();
		}
		if(paperRule.getSingle_choice_num()!=cpt[0] ||
				paperRule.getJudge_num()!=cpt[1] ||
				paperRule.getBlank_num()!=cpt[2] ||
				paperRule.getTranslate_num()!=cpt[3] ||
				paperRule.getSimple_question_num()!=cpt[4] ||
				paperRule.getCompute_num()!=cpt[5] ||
				paperRule.getMix_num()!=cpt[6]
				) {
			req.setAttribute("info", "各题型个数和章节分布时的个数不相同");
			return ERROR;
		}
		
		info = this.validateUtil.isDate(start);
		if(info!=null) {
			req.setAttribute("info", "日期填写错误,请重试!");
			return ERROR;
		}
		info = this.validateUtil.isDate(end);
		if(info!=null) {
			req.setAttribute("info", "日期填写错误,请重试!");
			return ERROR;
		}
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			Date date = format.parse(start);
			long start_time = date.getTime();
			long create_time = System.currentTimeMillis();
			//检查开始时间
			if(start_time<create_time) {
				req.setAttribute("info", "日期填写错误,请重试!");
				return ERROR;
			}
			paperRule.setStart_time(start_time);
			date = format.parse(end);
			long end_time = date.getTime();
			//检查结束时间
			if(end_time<start_time || end_time<create_time) {
				req.setAttribute("info", "日期填写错误,请重试!");
				return ERROR;
			}
			paperRule.setEnd_time(end_time);
			
		} catch (ParseException e) {
			e.printStackTrace();
			req.setAttribute("info", "日期格式错误,请重试!");
			return ERROR;
		}
		
		//验证规则是否重复
		int row = paperRuleService.getIdByMajorAndSubject(paperRule.getMajor().getM_id(), paperRule.getSubject().getSub_id());
		if(row != 0) {
			req.setAttribute("info", "该专业科目已有试卷生成规则,请勿重复添加!");
			return ERROR;
		}
		
		int id = this.paperRuleService.add(paperRule);
		if(id > 0) {
			//添加成功
			//更新考场信息
					ExamInfo ei = new ExamInfo();
					ei.setP_id(id);
					ei.getMajor().setM_id(paperRule.getMajor().getM_id());
					ei.getSubject().setSub_id(paperRule.getSubject().getSub_id());
					this.examInfoService.add(ei);
			return SUCCESS;
		} else {
			//添加失败
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
	}
	
	public String list() {
		List<PaperRule> list = this.paperRuleService.getAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		return "list";
	}
	
	public String delete() {
		//验证id信息
		PaperRule p = this.paperRuleService.getByIdEager(paperRule.getP_id());
		if(p == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		List<Paper> list = this.paperService.getPaperByPid(this.paperRule.getP_id());
		for(Paper pap : list) {
			String info = this.deletePaper(pap.getPap_id());
			if(!info.equals("success")) {
				req.setAttribute("info", "删除试卷规则下的试卷失败,请检查后重试!");
				return ERROR;
			}
		}
		
		this.paperRuleService.delete(this.paperRule.getP_id());
		
		return SUCCESS;
	}
	
	/**
	 * 删除试卷,同时删除本地存的试卷
	 * @return
	 */
	public String deletePaper(String pap_id) {
		Paper p = this.paperService.getByIdEager(pap_id);
		if(p == null) {
			req.setAttribute("info", "删除失败");
			return ERROR;
		}
		 //0:刚生成, 1:已绑定 2:考试中, 3 已交卷, 4 已阅卷, 5,已废弃
		if(p.getStatus()==0 || p.getStatus()==4||p.getStatus()==5) {
			//得到试卷路径
			String url = p.getPap_url();
			//删除
			this.paperService.delete(p);
			
			//将答案清除
			ObjectiveAnswer oAnswer = this.objectiveAnswerService.getByUuid(pap_id);
			this.objectiveAnswerService.delete(oAnswer.getAnswer_id());
			List<SubjectiveAnswer> sList = this.subjectiveAnswerService.getByUuid(pap_id);
			for(int i=0; i<sList.size(); i++) {
				this.subjectiveAnswerService.delete(sList.get(i).getAnswer_id());
			}
			
			//  student/more/paper/4028b88162a5164a0162a51752ae0006.html
			String tmp = this.getClass().getResource("/").getPath();
			int end = tmp.indexOf("WEB-INF");
			String path = tmp.substring(0,end) + url;
			File f = new File(path);
			if(f.delete()) {
				return SUCCESS;
			}else {
				req.setAttribute("info", "删除失败");
				return ERROR;
			}
		}else {
			req.setAttribute("info", "此试卷的状态不可删除");
			return ERROR;
		}
	}
	
	public String edit() {
		PaperRule pr = this.paperRuleService.getById(paperRule.getP_id());
		if(pr == null) {
			ServletActionContext.getRequest().setAttribute("info", "系统错误,请稍后重试");
			return ERROR;
		}
		ServletActionContext.getRequest().setAttribute("rule", pr);
		return "edit";
	}
	
	public String update() {
		//验证信息
		String start = req.getParameter("beginTime");
		String end = req.getParameter("endTime");
		//检查数据
		if(paperRule.getP_name().trim().equals("")) {
			req.setAttribute("info", "规则名不能为空!");
			return ERROR;
		}
		if(paperRule.getBlank_score()+paperRule.getJudge_score()+paperRule.getSingle_choice_score()+
				paperRule.getTranslate_score()+paperRule.getSimple_question_score()+paperRule.getCompute_score()
				+paperRule.getMix_score() != paperRule.getFull_score()
				) {
			req.setAttribute("info", "各题型分数和总分不一致!");
			return ERROR;
		}
		
		info = this.validateUtil.isDate(start);
		if(info!=null) {
			req.setAttribute("info", "日期填写错误,请重试!");
			return ERROR;
		}
		info = this.validateUtil.isDate(end);
		if(info!=null) {
			req.setAttribute("info", "日期填写错误,请重试!");
			return ERROR;
		}
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			Date date = format.parse(start);
			long start_time = date.getTime();
			long create_time = System.currentTimeMillis();
			//检查开始时间
			if(start_time<create_time) {
				req.setAttribute("info", "日期填写错误,请重试!");
				return ERROR;
			}
			paperRule.setStart_time(start_time);
			date = format.parse(end);
			long end_time = date.getTime();
			//检查结束时间
			if(end_time<start_time || end_time<create_time) {
				req.setAttribute("info", "日期填写错误,请重试!");
				return ERROR;
			}
			paperRule.setEnd_time(end_time);
			
		} catch (ParseException e) {
			e.printStackTrace();
			req.setAttribute("info", "日期格式错误,请重试!");
			return ERROR;
		}
		this.paperRuleService.update(paperRule);
		return SUCCESS;
	}
	
	public String detail() {
		PaperRule rule = this.paperRuleService.getById(paperRule.getP_id());
		if(rule == null) {
			ServletActionContext.getRequest().setAttribute("info", "无此信息,请重试或联系维护人员");
			return ERROR;
		}
		ServletActionContext.getRequest().setAttribute("rule", rule);
		return "detail";
	}
}
