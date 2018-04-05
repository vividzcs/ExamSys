<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="exam">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">


    <link href="../../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../../style/css/animate.css" rel="stylesheet">
    <link href="../../style/css/style.css?v=2.2.0" rel="stylesheet">
		<script>
			function form_check(obj){
						//验证密码一致
						
						var passworda =obj.passwordFirst.value;
						var passwordb =obj.passwordConfirm.value;
						
						if(passworda!=passwordb)
						{
							alert("密码两次不一致");
						}
						
						
						//验证姓名
						var id= /[u4e00-u9fa5]/;//纯汉字的正则表达式
						var sid=obj.studentName.value;
						if(id.test(sid)){					
							alert("姓名输入格式不正确");		
					}	
			}

		</script>
</head>

<body ng-controller="department">
             	 <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
	<div class="col-lg-6">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>学生个人信息修改</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                   
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="studentInformationForm" action="${pageContext.request.contextPath }/student/more/student_update.action" method="post">
                                   	
                                   	<div class="form-group">
                                        <label class="col-sm-3 control-label">旧密码：</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="oldPass" class="form-control" type="password">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码：</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="s_pass" class="form-control" type="password">
                                        </div>
                                    </div>
                                   
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">确认密码：</label>
                                        <div class="col-sm-8">
                                            <input id="confirm_password" name="passConfirm" class="form-control" type="password">
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
               
    <!-- Mainly scripts -->
    <script src="../../style/js/jquery-2.1.1.min.js"></script>
    <script src="../../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../../style/js/hplus.js?v=2.2.0"></script>
    <script src="../../style/js/plugins/pace/pace.min.js"></script>

 <!-- jQuery Validation plugin javascript-->
    <script src="../../style/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../../style/js/plugins/validate/messages_zh.min.js"></script>
       <!-- iCheck -->
    <script src="../style/../js/plugins/iCheck/icheck.min.js"></script>
    <script src="../style/../js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="../style/../js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../style/../js/plugins/dataTables/dataTables.bootstrap.js"></script>
    
    <script>
        //以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
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
           

            // validate signup form on keyup and submit
            $("#studentInformationForm").validate({
                rules: {
                    firstname: "required",
                    
                    password: {
                        required: true,
                        minlength: 5
                    },
                    confirm_password: {
                        required: true,
                        minlength: 5,
                        equalTo: "#password"
                    },
                    
                },
                messages: {
                    firstname: "请输入你的姓",
                    
                    password: {
                        required: "请输入您的密码",
                        minlength: "密码必须5个字符以上"
                    },
                    confirm_password: {
                        required: "请再次输入密码",
                        minlength: "密码必须5个字符以上",
                        equalTo: "两次输入的密码不一致"
                    },
                    
                }
            });

           
            
        });
    </script>
</body>

</html>