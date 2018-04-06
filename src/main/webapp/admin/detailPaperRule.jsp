<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html  ng-app="exam">

<head >

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>学生考试系统</title>
    <meta name="keywords" content="学生考试系统，专为学生打造在校考试功能">
    <meta name="description" content="大学生在线考试系统，学生在网络上在线填写试卷">

    <link href="../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
      <link rel="stylesheet" href="../style/css/self.css" />
      <style>
      	width: 50%;
      }
      table{
      	 	margin-top: 5px;
      }
      table th{
      	text-align: center;
     
      }
      table tbody td{
      	padding: 5px;
      }
      table tbody td input[type='text']{
       width: 120px;
      }
      </style>
</head>

<body ng-controller="profess">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
				       <div class="col-lg-12" class="paperRule">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h3>****试卷规则查看</h3>
                               <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="paperRuleForm" style="width:50%">
                                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">专业</label>
                                        <div class="col-sm-8">
                                            <p>${rule.p_name }</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">科目</label>
                                        <div class="col-sm-8">
                                           <p>${rule.subject.sub_name }</p>
                                        </div>
                                    </div>
                                     <div class="form-group">
                                        <label class="col-sm-3 control-label">开始时间</label>
                                        <div class="col-sm-8">
                                        	<jsp:useBean id="start" class="java.util.Date"></jsp:useBean>
											 <jsp:setProperty property="time" name="start" value="${rule.start_time }"/>
                                        	<p><fmt:formatDate value="${start }" pattern="yyyy/MM/dd HH:mm"/></p>
                                        </div>
                                    </div>
                                     <div class="form-group">
                                        <label class="col-sm-3 control-label">结束事件</label>
                                        <div class="col-sm-8">
                                        	<jsp:useBean id="end" class="java.util.Date"></jsp:useBean>
											 <jsp:setProperty property="time" name="end" value="${rule.end_time }"/>
                                        	<p><fmt:formatDate value="${end }" pattern="yyyy/MM/dd HH:mm"/></p>
                                        </div>
                                    </div>
                                 <div class="form-group">
                                        <label class="col-sm-3 control-label">总分</label>
                                        <div class="col-sm-8">
                                        	<p>${rule.full_score }</p>
                                        </div>
                                    </div>
                                     <label class="col-sm-3 control-label">试卷题目信息</label>

                                    	<table>
                                    		<thead>
                                    		<tr>
                                    			<th>题目类型</th>
                                    			<th>个数</th>
                                    			<th>题型总分数</th>
                                    		</tr>
                                    		</thead>
                                    		<tbody>
                                    		<tr>
                                    			<td>单选</td>
                                    			<td>${rule.single_choice_num }</td>
                                    		    <td>  ${rule.single_choice_score }  </td>
                                    		</tr>
                                    		 <tr>
                                    			<td>判断</td>
                                    			<td>${rule.judge_num }</td>
                                    		    <td>  ${rule.judge_score } </td> </td>
                                    		 </tr>
                                    		 <tr>
                                    			<td>填空</td>
                                    			<td>${rule.blank_num }</td>
                                    		    <td>  ${rule.blank_score }  </td>
                                    		 </tr>
                                    		 <tr>
                                    			<td>名词解释</td>
                                    			<td>${rule.translate_num }</td>
                                    		    <td>  ${rule.translate_score }  </td>
                                    		 </tr>
                                    		 	 <tr>
                                    			<td>简答</td>
                                    			<td>${rule.simple_question_num }</td>
                                    		    <td>  ${rule.simple_question_score }  </td>
                                    		 </tr>
                                    		 	 <tr>
                                    				<td>计算</td>
                                    			<td>${rule.compute_num }</td>
                                    		    <td>  ${rule.compute_score }  </td>
                                    		 </tr>
                                    		 	 <tr>
                                    				<td>综合</td>
                                    			<td>${rule.mix_num }</td>
                                    		    <td>  ${rule.mix_score }  </td>
                                    		 </tr>
                                    		 <tr>
                                    			<td>备注</td>
                                    			<td colspan="2">${rule.p_desc }</td>
                                    		 </tr>
                                    		</tbody>
                                    	</table>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                                <button class="btn btn-primary addStudet"  > <a href="lookPaperRule.html" style="color: white;">返回</a></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
<script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
    
       <!-- DROPZONE -->
    <script src="../style/js/plugins/dropzone/dropzone.js"></script>
   
    <!--angularjs-->
        <!--引入angular插件-->
    <script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
    <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
    <script src="../style/js/anjularJS/anjularjs/app.js"></script>
    <script src="../style/js/anjularJS/anjularjs/control.js"></script>-->
    <!-- jQuery Validation plugin javascript 
    <script src="../style/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../style/js/plugins/validate/messages_zh.min.js"></script>

    <!-- iCheck -->
    <script src="../style/js/plugins/iCheck/icheck.min.js"></script>
    <script>
      $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"
       

        });

         //以下为官方示例
        $().ready(function () {
             
        });
</script>


</body>

</html>