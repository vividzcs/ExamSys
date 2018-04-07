package com.nwuer.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class FreemarkerUtil {
	private Configuration config = new Configuration(Configuration.getVersion());
	
	private String path;
	
	public FreemarkerUtil() {
		String tmp = this.getClass().getResource("/").getPath();
		int end = tmp.indexOf("WEB-INF");
		path = tmp.substring(0,end);
	}
	/**
	 * 生成试卷份数
	 * @param data 所要放的数据
	 * @param name 生成试卷的名字
	 * @param number  生成试卷的份数
	 * @return
	 */
	public String makePaper(Map data,String name) {
		Writer out = null;
		String paperPath = null;
		try {
			//1 创建一个Configuration对象
			//2 设置模板保存的目录
			config.setDirectoryForTemplateLoading(new File(path + "WEB-INF/ftl"));
			//3 设置模板文件的编码格式
			config.setDefaultEncoding("UTF-8");
			//4 加载一个模板文件,创建一个模板对象
			Template template = config.getTemplate("paper.ftl");
			//5 创建一个数据集
			//6 放数据
			
			//7 创建一个Writer对象,指定输出文件的路径及文件名
			String paperPathDict = path+"student/more/paper/";
			File f = new File(paperPathDict);
			if(!f.exists()) {
				f.mkdirs();
			}
			paperPath = paperPathDict+  name+".html";
			out = new FileWriter(paperPath);
			
			//8 生成静态页面
			template.process(data, out);
			 
//			return true;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				try {
					if(out != null)
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		return paperPath;
	}
	
	/**
	 * 生成试卷份数
	 * @param data 所要放的数据
	 * @param name 生成试卷的名字
	 * @param number  生成试卷的份数
	 * @return
	 */
	public String makePracticePaper(Map data,String name) {
		Writer out = null;
		String paperPath = null;
		try {
			//1 创建一个Configuration对象
			//2 设置模板保存的目录
			config.setDirectoryForTemplateLoading(new File(path + "WEB-INF/ftl"));
			//3 设置模板文件的编码格式
			config.setDefaultEncoding("UTF-8");
			//4 加载一个模板文件,创建一个模板对象
			Template template = config.getTemplate("practicePaper.ftl");
			//5 创建一个数据集
			//6 放数据
			
			//7 创建一个Writer对象,指定输出文件的路径及文件名
			String paperPathDict = path+"student/practice/";
			File f = new File(paperPathDict);
			if(!f.exists()) {
				f.mkdirs();
			}
			paperPath = paperPathDict+  name+".html";
			out = new FileWriter(paperPath);
			
			//8 生成静态页面
			template.process(data, out);
			 
//			return true;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				try {
					if(out != null)
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		return paperPath;
	}
	
}
