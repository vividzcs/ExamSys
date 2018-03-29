<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>学生考试系统</title>
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
	</head>
	<body>
		   <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
		    <p> 
				       <div class="col-lg-12" ng-controller="teacher">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加老师基本信息</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="addTeacherForm" action="${pageContext.request.contextPath }/admin/teacher_updateA.action" method="post">
                                     
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">姓名</label>
                                        <input type="hidden" name="t_id" value="${teacher.t_id }">
                                        <div class="col-sm-8">
                                            <input id="username" name="t_name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error tname" value="${teacher.t_name }">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">工号</label>
                                        <div class="col-sm-8">
                                            <input id="usernumber" name="t_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error tnumber" value="${teacher.t_number }">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="t_pass" class="form-control" type="text" class="tpassword" value="${teacher.t_pass }">
                                        </div>
                                    </div>
                                   
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">性别</label>
                                        <div class="col-sm-8">
                                       <span class="sex">男</span><input   type="radio" name="t_sex" value="1" <c:if test="${teacher.t_sex eq 1 }">checked="checked"</c:if> > 
                                       <span class="sex">女</span><input   type="radio" name="t_sex" value="0" <c:if test="${teacher.t_sex eq 0 }">checked="checked"</c:if>>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                           <select class="form-control m-b"  ng-model="selected" ng-options="m for m in tdepartment" name="academy.a_id">
                                                 <option value="">-- 请选择 --</option>
                                                 <c:forEach items="${list }" var="academy">
                                                 <option value="${academy.a_id }" <c:if test="${teacher.academy.a_id eq academy.a_id }">selected="selected"</c:if> >${academy.a_name }</option>
                                                 </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary addStudet" type="submit"> 确认添加</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
                   
				      </p>
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
         //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();
            // validate signup form on keyup and submit
            $("#addTeacherForm").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 2
                    },
                      usernumber: "required",
                    password: {
                        required: true,
                        minlength: 5
                    },
                    
                },
                messages: {
                    username: {
                        required: "请输入老师用户名",
                        minlength: "用户名必须两个字符以上"
                    },
                    usernumber:'输入学号',
                    password: {
                        required: "请输入老师密码",
                        minlength: "密码必须5个字符以上"
                    },
                }
            });
              
        });
        </script>
	</body>
</html>