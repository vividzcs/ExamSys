<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="exam">

<head>

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
    <!-- Data Tables -->
    <link href="../style/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
    <link href="../style/css/simple-sidebar.css" rel="stylesheet">
	<link href="../style/css/index.css" rel="stylesheet">
	<link href="../style/css/self.css" rel="stylesheet">
</head>

<body ng-controller="profess">
    
<!--侧栏部分结束-->
        <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
            	<!--上传文件-->
            	<div class="emportPaper col-lg-12">
            		 <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <span class="title">导出题库</span>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="signupForm" action="${pageContext.request.contextPath }/admin/admin_downloadQuestionBank.action" method="post">
                                           <div class="form-group">
                                        <label class="col-sm-3 control-label">题库类型</label>
                                        <div class="col-sm-8">
                                              <select class="form-control m-b" style="width: 50%" name="kind">
                                                 <option value="0">单选</option>
                                                 <option value="1">判断</option>
                                                 <option value="2">主观题</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">导出题库</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                  </div>
                </div>
             </div>
  
 <script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="../style/js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/sidebar_menu.js"></script>
    <!--angularjs-->
        <!--引入angular插件-->
    <script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
    <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
    <script src="../style/js/anjularJS/anjularjs/app.js"></script>
    <script src="../style/js/anjularJS/anjularjs/control.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
    <script src="../style/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../style/js/plugins/validate/messages_zh.min.js"></script>
   
     <script src="../style/js/sidebar_menu.js"></script>
	<script src="../style/js/plugins/layer/layer.min.js"></script>
	<script src="../style/js/contabs.min.js" type="text/javascript" ></script>
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
            $("#signupForm").validate({
                rules: {
                    Bname: "required",
                },
                messages: {
                    Bname: "请输入题库名称",
                }
            });
        });

    </script>
    <script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
   <script src="../style/js/anjularJS/anjularjs/app.js"></script>
   <script src="../style/js/anjularJS/anjularjs/control.js"></script>
   
</body>

</html>