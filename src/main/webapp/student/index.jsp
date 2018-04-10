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
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">

                        <div class="dropdown profile-element">  
                        	<c:if test="${sessionScope.student != null }">
                        		<a data-toggle="dropdown" class="dropdown-toggle" href="./">
								<span class="clear"> <span class="block m-t-xs">  <!-- <strong class="font-bold">Beaut-zihan</strong> -->
								</span> <span class="text-muted text-xs block">你好,${sessionScope.student.s_name }同学</span> </span>
							</a>
                        	</c:if>
                        	<c:if test="${sessionScope.student == null }">
                            <span data-toggle="dropdown" class="dropdown-toggle">
                                <span class="clear"> <span class="block m-t-xs"> 
                             </span><a class="J_menuItem"  href="${pageContext.request.contextPath }/student/loginStudent.jsp">登录</a></span>
                            </span>
                        	</c:if>
                        </div>
                       
					<li class="active">
                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">考试</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem"  href="${pageContext.request.contextPath }/student/more/signInExam.jsp">注册考试</a>
                            </li>
                            <li><a class="J_menuItem"  href="${pageContext.request.contextPath }/student/more/loginExam.jsp">录登考试</a>
                            </li>
                        </ul>
                    </li>
                    </li>
                   
					<li>
                        <a class="J_menuItem"  href="${pageContext.request.contextPath }/student/more/student_edit.action"><i class="fa fa-columns"></i> <span class="nav-label">修改个人信息</span></a>
                    </li>
					
					<li>
                        <a class="J_menuItem"  href="${pageContext.request.contextPath }/student/practice.jsp"><i class="fa fa-edit"></i> <span class="nav-label">练习</span></a>
                    </li>
					
                </ul>

            </div>
        </nav>
 <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="indexStudent.html" title="返回首页"><i class="fa fa-home"></i></a>欢迎登录在线考试系统后台管理</span>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath }/student/logout.action">
                                <i class="fa fa-sign-out"></i> 退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header">
  <!--应用框架来弄-->
          <div class="row J_mainContent" id="content-main">
			<iframe class="J_iframe" name="iframe0" width="100%" height="900px" src="../hello.html" frameborder="0" data-id="hello.html" seamless></iframe>
		   </div>
            <div class="footer">
                    <div class="pull-right">
                        By：<a href="http://www.zi-han.net" target="_blank">nwu:</a>
                    </div>
                    <div>
                        <strong>Copyright</strong> H+ &copy; 2014
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
	<script src="../style/js/plugins/layer/layer.min.js"></script>
	<!--<script src="../style/js/hplus.min.js"></script>-->
	<!-- 操作提示 -->
	<script src="../style/js/plugins/noty/jquery.noty.packaged.js" type="text/javascript" ></script>
	
	<!-- 演示用的js，实际开发中可以另行创建，并删除这个保证系统简洁 -->
	<script src="../style/js/noties.js" type="text/javascript" ></script>
    <script src="../style/js/sidebar_menu.js"></script>
	<script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="../style/js/contabs.min.js" type="text/javascript" ></script>
</body>

</html>