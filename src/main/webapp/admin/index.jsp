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

    <link href="../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <!-- Gritter -->
    <link href="../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body  style="height: 100%;">
    <div id="wrapper">
      <nav class="navbar-default navbar-static-side" role="navigation">
        	<!--左侧栏部分-->
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">

                        <div class="dropdown profile-element"> 
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold"> admin</strong>
                             </span> <span class="text-muted text-xs block">超级管理员  </span> </span>
                            </a>
                        </div>
                        <div class="logo-element">
                            H+
                        </div>

                    </li>
                    <li  >
                        <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">题库维护</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="${pageContext.request.contextPath }/admin/importQbank.jsp">导入题库</a>
                            </li>
                            <li><a class="J_menuItem" href="${pageContext.request.contextPath }/admin/showExport.action">导出题库</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa fa-globe"></i> <span class="nav-label">人员维护信息</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                               <li><a class="J_menuItem" href="mangeStudent.html">管理学生信息</a>
                            </li>
                            <li><a class="J_menuItem" href="${pageContext.request.contextPath }/admin/teacher_list.action">管理教师信息</a>
                            </li>
                            <li><a class="J_menuItem" href="${pageContext.request.contextPath }/admin/manageSelf.jsp">管理个人信息</a>
                            </li>
                            <li><a class="J_menuItem" href="${pageContext.request.contextPath }/admin/department_list.action">管理院系信息</a>
                            </li>
                            <li><a class="J_menuItem" href="${pageContext.request.contextPath }/admin/profess_list.action">管理专业信息</a>
                            </li>
                            <li><a class="J_menuItem" href="${pageContext.request.contextPath }/admin/school_edit.action">管理学校信息</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">试卷维护</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="paperRule.html">试卷生成规则</a>
                            </li>
                            <li><a class="J_menuItem" href="lookpaperRule.html">查看试卷规则</a>
                            </li>
                            <li><a class="J_menuItem" href="createPaper.html">生成考试试卷</a>
                            </li>
                            <li><a class="J_menuItem" href="importPaper.html">导出考试试卷</a>
                            </li> <li><a class="J_menuItem" href="changePaper.html">更换考试试卷</a>
                            </li>
                            <li><a  class="J_menuItem" href="createTestPaper.html">生成练习试卷</a>
                            </li>
                         
                         
                        </ul>
                  </li>
                    <li>
                        <a href="maintainCourse.html" class="J_menuItem"><i class="fa fa-laptop"></i> <span class="nav-label">课程信息维护</span></a>
                    </li>
                    <li>
                        <a href="index.html#"  ><i class="fa fa-picture-o"></i> <span class="nav-label">考场信息监控</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="emportStatus.html"   class="J_menuItem">导出考试状态</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
<!--侧栏部分结束-->
<!--右侧的部分-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
         
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="indexAdmin.html" title="返回首页"><i class="fa fa-home"></i></a>欢迎登录在线考试系统后台管理</span>
                        </li>
                        <li>
                            <a href="../index.html">
                                <i class="fa fa-sign-out"></i> 退出
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="overflow: hidden;">
  <!--应用框架来弄-->
              <div class="row J_mainContent" id="content-main">
			   <iframe class="J_iframe" name="iframe0" width="100%" height="900px" src="hello.html" frameborder="0" data-id="hello.html" seamless></iframe>
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
    <script src="../style/js/jquery.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
	<script src="../style/js/plugins/layer/layer.min.js"></script>
	<!--<script src="../style/js/hplus.min.js"></script>-->
	<!-- 操作提示 -->
	<!--<script src="../style/js/plugins/noty/jquery.noty.packaged.js" type="text/javascript" ></script>-->
	
	<!-- 演示用的js，实际开发中可以另行创建，并删除这个保证系统简洁 -->
	<script src="../style/js/noties.js" type="text/javascript" ></script>
    <script src="../style/js/sidebar_menu.js"></script>
	<script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="../style/js/contabs.min.js" type="text/javascript" ></script>
   <!--对于框架的高度进行设置-->
   <script>
   $("iframe").height("900px")
   </script>
</body>

</html>