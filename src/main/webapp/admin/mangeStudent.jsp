<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>学生考试系统</title>
    <meta name="keywords" content="学生考试系统，专为学生打造在校考试功能">
    <meta name="description" content="大学生在线考试系统，学生在网络上在线填写试卷">

    <link href=" ../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href=" ../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../../ css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../../ js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href=" ../style/css/animate.css" rel="stylesheet">
    <link href=" ../style/css/style.css?v=2.2.0" rel="stylesheet">
    <!--<link rel="stylesheet" type="text/css" href="../../style/css/self.css"/>-->

</head>

<body ng-app="exam">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
          <div class="white-bg" style="height: 100%;">
			<div class="col-lg-8" style="height: 500px;">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>学校信息修改</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="schoolInformationForm">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">编号</label>
                                        <div class="col-sm-8">
                                            <input id="snumber" name="snumber" class="form-control" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">名称</label>
                                        <div class="col-sm-8">
                                            <input id="sname" name="sname" class="form-control" type="type">
                                        </div>
                                    </div>
                                   
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">简介</label>
                                        <div class="col-sm-8">
                                        	<textarea id="sintroduce" name="sintroduce" class="form-control"></textarea>
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">院系数</label>
                                        <div class="col-sm-8">
                                            <input id="dnumber" name="dnumber" class="form-control" type="type">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">专业数</label>
                                        <div class="col-sm-8">
                                            <input id="pnumber" name="pnumber" class="form-control" type="type">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">修改</button>
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

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
    
       <!-- DROPZONE -->
    <script src="../style/js/plugins/dropzone/dropzone.js"></script>
   
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
            $("#schoolInformationForm").validate({
                rules: {
                    sname: {
                        required: true,
                    },
                     snumber: "required",
                    dnumber: {
                        required: true,
                    },
                    pnumber:"required",
                    sintroduce:"required"
                    
                },
                messages: {
                   sname: "输入学校名称",
                    snumber:'输入学校编号',
                   dnumber:  "输入院系数",
                   pnumber:'输入专业数',
                   sintroduce:"输入学校描述"
                    
                }

            });
        });
</script>

</body>

</html>