<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

</head>

<body>
            <div class="ibox-content  rows" style="height: 100%;">
            	<div class="col-lg-12">
                                <form class="form-horizontal m-t" id="signupForm" action="${pageContext.request.contextPath }/admin/admin_findTeacher.action" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">工号：</label>
                                        <div class="col-sm-8">
                                            <input id="studentID" name="t_number" class="form-control" type="text">
                                        </div>
                                    </div>
									<div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">查询</button>
                                        </div>
                                    </div>
								<form>
			</div>

                <div class="ibox-content">
                                <table class="table table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                        <tr>
                                        	 <th>姓名</th>
                                            <th>工号</th>
                                            <th>密码</th>
                                            <th>创建时间</th>
                                            <th>上次登录时间</th>
                                            <th>院系</th>
                                            <th>状态</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list }" var="teacher">
                                        <tr class="gradeX">
                                            <td>${teacher.t_name }</td>
                                            <td>${teacher.t_number }</td>
                                            <td>${teacher.t_pass }</td>
                                            <jsp:useBean id="create_time" class="java.util.Date"></jsp:useBean>
	                        				<jsp:setProperty property="time" name="create_time" value="${teacher.create_time }"/>
                                            <td> <fmt:formatDate value="${create_time }" pattern="yyyy-MM-dd"/> </td>
                                            <jsp:useBean id="last_login" class="java.util.Date"></jsp:useBean>
                                            <jsp:setProperty property="time" name="last_login" value="${teacher.last_login }"/>
                                            <td><fmt:formatDate value="${last_login }" pattern="yyyy-MM-dd"/></td>
                                            <td>${teacher.academy.a_name }</td>
                                            <td>${teacher.status }</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    </tfoot>
                                </table>
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