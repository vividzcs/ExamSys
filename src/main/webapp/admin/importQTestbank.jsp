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
    <!--<link href="../style/css/plugins/dropzone/basic.css" rel="stylesheet">-->
    <!--<link href="../style/css/plugins/dropzone/dropzone.css" rel="stylesheet"><link href="../style/css/animate.css" rel="stylesheet">-->
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
    <link rel="stylesheet" href="../style/css/self.css" />
</head>
<body ng-controller="profess">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
            	<!--上传文件-->
            	<div class="importPaper col-lg-12">
            		<div class="col-lg-12">
                        <div class="ibox ">
                            <div class="ibox-title">
                                <span class="title">导入题库</span>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="signupForm" action="${pageContext.request.contextPath }/admin/admin_importQuestionTestBank.action" method="post" enctype="multipart/form-data">
                                      <div class="form-group" >
                                        <label class="col-sm-3 control-label">练习题库类型</label>
                                        <div class="col-sm-8">
                                              <select class="form-control m-b"  style="width:50%" name="kind">
                                                 <option value="0">单选</option>
                                                 <option value="1">判断</option>
                                                 <option value="2">主观题</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">上传练习题库</label>
                                        <div class="col-sm-8">
                                            <input id="upflie" type="file" name="file_upload" type="text" aria-required="true" aria-invalid="true" class="error">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">导入练习题库</button>
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">练习题库模板</label>
                                        <div class="col-sm-8">
                                            <a href="${pageContext.request.contextPath }/modellist/题库模板.rar">下载模板</a>
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
    
       <!-- DROPZONE -->
    <script src="../style/js/plugins/dropzone/dropzone.js"></script>
       <!-- 新 Bootstrap 核心 CSS 文件 -->
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

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
            $("#signupForm").validate({
                rules: {
                    Bname: "required",
                    Dname: "required",
                },
                messages: {
                    Bname: "请输入题库名称",
                    Dname: "请输入题库描述",
                }
            });
        });

    </script>
     <!--引入必须的anjular-->
  <script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
   <script src="../style/js/anjularJS/anjularjs/app.js"></script>
   <script src="../style/js/anjularJS/anjularjs/control.js"></script>
   
    <script>
    	//写联动的下拉条
    </script>


</body>

</html>