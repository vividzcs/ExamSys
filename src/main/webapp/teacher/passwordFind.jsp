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

</head>

<body>
            <div class="ibox-content  rows" style="height: 100%;">
            	<div class="col-lg-12">
                                <form class="form-horizontal m-t" id="signupForm" action="${pageContext.request.contextPath }/teacher/teacher_findStu.action" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">学号：</label>
                                        <div class="col-sm-8">
                                            <input id="studentID" name="s_number" class="form-control" type="text">
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
                                        	 <th>学生姓名</th>
                                            <th>学生学号</th>
                                            <th>密码</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="gradeX">
                                            <td>${student.s_name }</td>
                                            <td>${student.s_number }</td>
                                            <td>${student.s_pass }</td>
                                        </tr>
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