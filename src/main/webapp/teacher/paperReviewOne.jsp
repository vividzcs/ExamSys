<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

  

    <link href="../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
    <link rel="stylesheet" href="../style/plugins/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript" src="../style/plugins/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<script>
$(function(){SyntaxHighlighter.highlight(),$("table.syntaxhighlighter").each(function(){if(!$(this).hasClass("nogutter")){var h=$($(this).find(".gutter")[0]),i=$($(this).find(".code .line"));h.find(".line").each(function(h){$(this).height($(i[h]).height()),$(i[h]).height($(i[h]).height())})}})});

</script>
    

	
</head>

<body>
			<div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
			 <tr><td align="center">试卷编号:</td><td>${list[0].uuid }</td></tr><!--从页面传值 未实现-->
			       <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content"><!--动态生成题目答案的展现个数 题号也由数据库产生-->
                                <form class="form-horizontal m-t" id="scoreForm" action="${pageContext.request.contextPath }/teacher/teacher_pushReview.action">
                                	<input type="hidden" name="uuid" value="${list[0].uuid }" />
                                    <c:forEach items="${list }" var="sAnswer">
									<%--0:名词解释,1:填空,2:简答,3:计算,4:综合  --%>
									<c:if test="${sAnswer.kind eq 0 }">
									<div class="form-group">
										<label class="col-sm-3 control-label">题号：名词解释第${sAnswer.sequence}题</label>									
											 <div class="col-sm-8">
	                 	    题目     <textarea readonly class=form-control><!--${sAnswer.answer_question}  --></textareas>
	                 	    考生作答：<textarea readonly>${sAnswer.answer_write}</textarea>
	                                            答案：<textarea readonly class=form-control>${sAnswer.answer_right}</textarea>
	                                        </div>
											
	                                    </div>
		                                <div class="form-group">
	                                        <div class=" col-sm-offset-3">
	                                            评分:<input  name="_${sAnswer.kind }_${sAnswer.sequence }" class="form-control" required="" aria-required="true"/> 单个分数:${rule.translate_score/rule.translate_num }
	                                        </div>
	                                    </div>
									</c:if>
									<c:if test="${sAnswer.kind eq 1 }">
									<div class="form-group">
										<label class="col-sm-3 control-label">题号：填空第${sAnswer.sequence}题</label>									
										<div class="col-sm-8">
	                 	    题目     <textarea readonly class=form-control>${sAnswer.answer_question}</textarea>
	                 	    考生作答：<textarea readonly class=form-control>${sAnswer.answer_write}</textarea>
	                                            答案：<textarea readonly class=form-control>${sAnswer.answer_right}</textarea>
	                                        </div>
											
	                                    </div>
		                                <div class="form-group">
	                                        <div class="col-sm-3 col-sm-offset-3">
	                                            评分:<input name="_${sAnswer.kind }_${sAnswer.sequence }" class="form-control" required="" aria-required="true"/>单个分数:${rule.blank_score/rule.blank_num }
	                                        </div>
	                                    </div>
									</c:if>
									<c:if test="${sAnswer.kind eq 2 }">
									<div class="form-group">
										<label class="col-sm-3 control-label">题号：简答第${sAnswer.sequence}题</label>									
										<div class="col-sm-8">
	                 	    题目     <textarea readonly class=form-control>${sAnswer.answer_question}</textarea>
	                                            考生作答：<textarea readonly class=form-control>${sAnswer.answer_write}</textarea>
	                                             答案：<textarea readonly class=form-control>${sAnswer.answer_right}</textarea>
	                                        </div>
											
	                                    </div>
		                                <div class="form-group">
	                                        <div class=" col-sm-offset-3">
	                                         评分:<input name="_${sAnswer.kind }_${sAnswer.sequence }" class="form-control" required="" aria-required="true"/>单个分数:${rule.simple_question_score/rule.simple_question_num }
	                                        </div>
	                                    </div>
									</c:if>
									<c:if test="${sAnswer.kind eq 3 }">
									<div class="form-group">
										<label class="col-sm-3 control-label">题号：计算第${sAnswer.sequence}题</label>									
										<div class="col-sm-8">
	                 	    题目     <textarea readonly class=form-control>${sAnswer.answer_question}</textarea>
	                 	    考生作答：<textarea readonly class=form-control>${sAnswer.answer_write}</textarea>
	                                            答案：<textarea readonly class=form-control>${sAnswer.answer_right}</textarea>
	                                        </div>
											
	                                    </div>
		                                <div class="form-group">
	                                        <div class="col-sm-offset-3">
	                                          评分:<input name="_${sAnswer.kind }_${sAnswer.sequence }" class="form-control" required="" aria-required="true"/>单个分数:${rule.compute_score/rule.compute_num }
	                                        </div>
	                                    </div>
									</c:if>
									<c:if test="${sAnswer.kind eq 4 }">
									<div class="form-group">
										<label class="col-sm-3 control-label">题号：综合第${sAnswer.sequence}题</label>									
										<div class="col-sm-8">
	                 	    题目     <textarea readonly class=form-control>${sAnswer.answer_question}</textarea>
	                 	    考生作答：<textarea readonly  class=form-control>${sAnswer.answer_write}</textarea>
	                                            答案：<textarea readonly class=form-control>${sAnswer.answer_right}</textarea>
	                                        </div>
											
	                                    </div>
		                                <div class="form-group">
	                                        <div class=" col-sm-offset-3">
	                                          评分:<input name="_${sAnswer.kind }_${sAnswer.sequence }" class="form-control" required="" aria-required="true"/>单个分数:${rule.mix_score/rule.mix_num }
	                                        </div>
	                                    </div>
									</c:if>
                                       
                                    </c:forEach>
									
                                    <div class="form-group">
                                        <div class="col-sm-4 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">提交</button>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button class="btn btn-primary" type="reset">重阅</button>
                                        </div>
                                    </div>


                                </form>
                            </div>
                        </div>
					</div>
				</div>
			</div>
			
           

    <!-- Mainly scripts -->
    <script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>


</body>

</html>