<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="exam">

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
	<!--<script>
			function form_check(obj){
				//验证密码一致
				
				var passworda =obj.passwordFirst.value;
				var passwordb =obj.passwordConfirm.value;
				
				if(passworda!=passwordb)
				{
					alert("密码两次不一致");
				}
				
				
				//验证用户名
				var name= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,16}$/;//"^[A-Za-z]+$"纯字母的正则表达式
				var mynamespell=obj.mynamespell.value;
				if(name.test(mynamespell)){
					if(mynamespell.length<8||mynamespell.length>16){
						alert("位数不符合规定");
						}
					}
				else{
					alert("必须包含字母与数字的组合");
				}
			}	

</script>-->
</head>

<body ng-controller="department">
			<div class="white-bg" style="height: 100%;">
			<div class="col-lg-8" style="height: 500px;">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>教师个人信息修改</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="teacherInformationForm" action="${pageContext.request.contextPath }/teacher/teacher_update.action" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">姓名：</label>
                                        <div class="col-sm-8">
                                            <input id="firstname" name="t_name" class="form-control" type="text" value="${teacher.t_name }">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码：</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="t_pass" class="form-control" type="password">
                                        </div>
                                    </div>
                                   
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">确认密码：</label>
                                        <div class="col-sm-8">
                                            <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                                        </div>
                                    </div>
                                        <div class="form-group">
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                            <select class="form-control m-b"  ng-model="selected" ng-options="m.department for m in departments" name="academy.a_id" ng-change="changeClassification(selected)">
                                                 <option value="">-- 请选择 --</option>
                                                 <c:forEach items="${list }" var="academy">
                                                 	<option value="${academy.a_id }" <c:if test="${academy.a_id eq teacher.academy.a_id }">selected="selected"</c:if> >
                                                 	${academy.a_name }
                                                 	</option>
                                                 </c:forEach>
                                            </select>
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

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js?v=3.4.0"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/hplus.js?v=2.2.0"></script>
    <script src="js/plugins/pace/pace.min.js"></script>

 <!-- jQuery Validation plugin javascript-->
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    <script src="js/plugins/validate/messages_zh.min.js"></script>
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
            $("#teacherInformationForm").validate({
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
                    firstname: "请输入你的姓名",
                    
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