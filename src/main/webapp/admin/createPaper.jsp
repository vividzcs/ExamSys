<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>学生考试系统</title>
    <meta name="keywords" content="学生考试系统，专为学生打造在校考试功能">
    <meta name="description" content="大学生在线考试系统，学生在网络上在线填写试卷">

    <link href="../style/css/bootstrap.min.css" rel="stylesheet">
    <!--<link href="../style/font-awesome/css/font-awesome.css" rel="stylesheet">-->

    <!-- Morris -->
    <link href="../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../style/css/plugins/datapicker/bootstrap-datetimepicker.css" rel="stylesheet"  >
    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
     <link rel="stylesheet" type="text/css" href="../style/css/self.css"/>
       <link rel="stylesheet" href="../style/css/solvePlay.css" />
         <style>
      select{
      	width: 50%;
      }
      </style>
</head>

<body  >
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
            	<div class="createPaper">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 生成考试试卷</h5>
                                  <i style="color:red">注意:若未创建题目类型,则填写为0</i>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                            	<div class="container">
                                <form class="form-horizontal m-t" id="createPaperForm"  role="form" action="${pageContext.request.contextPath }/admin/paper_add.action" method="post">
                                     <fieldset>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">试卷类型</label>
                                        <div class="col-sm-8">
                                                <select name="pap_kind" class="form-control m-b" style="width: 50%;"> <!--ng-model="selecteCourse" ng-options="m for m in courseStyle" -->
													<option value="0">练习试卷</option>                                                 
													<option value="1">考试试卷</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">试卷生成规则</label>
                                        <div class="col-sm-8">
                                              <select name="p_id" class="form-control m-b" style="width: 50%;" ng-model="selectePaper" ng-options="m for m in paperStyle" >
                                                 <option value="">-- 请选择 --</option>
                                                 <c:forEach items="${list }" var="rule">
                                                 	<option value="${rule.p_id }">${rule.p_name }</option>
                                                 </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                  
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">试卷数目</label>
                                        <div class="col-sm-8">
                                           <input id="papernum" placeholder="填写数字" style="width: 50%;" name="paperNum" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error tname">

                                        </div>
                                    </div>
                                       
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary addStudet" type="submit"> 生成试卷</button>
                                        </div>
                                    </div>
                                      </fieldset>
                                </form>
                                </div>
                            </div>
                        </div>
                      </div>
                    
            	</div>
            </div>
<!-- Mainly scripts -->
 
    <script src="../style/js/jquery-2.1.1.min.js" charset="UTF-8"></script>
    <script src="../style/js/bootstrap.min.js"></script>
        <!--<script  src="../style/js/plugins/datapicker/bootstrap-datepicker.js" charset="UTF-8"></script>
    <script  src="../style/js/plugins/datapicker/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>-->

    <!-- Custom and plugin javascript -->
    <!---日期插件-->
    
      <!--angularjs-->
        <!--引入angular插件-->
    <!--<script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
    <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
    <script src="../style/js/anjularJS/anjularjs/app.js"></script>
    <script src="../style/js/anjularJS/anjularjs/control.js"></script>-->
       <!-- DROPZONE -->
    <!-- jQuery Validation plugin javascript-->
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
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            $("#createPaperForm").validate({
                rules: {
                    papername:  "required",
                    papernum: "required",
                },
                messages: {
                  
                    papername:'输入生成的试卷名称',
                    papernumber:'填写试卷数量'
                }

            });
        
        });
 
</script>
<script type="text/javascript">
 $(function(){
 	$('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
 
 })
	 
   
    </script>

</body>

</html>