<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <!-- Data Tables -->
    <link href="../style/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
</head>

<body  >
        
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
            	   <div class="importPaper">
            	   	 <div class="wrapper wrapper-content animated fadeInRight">
                     <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 导出试卷</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">

                                <table class="table table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                        <tr>
                                        	 <th>专业</th>
                                        	 <th>科目</th>
                                        	 <th>考生名</th>
                                        	 <th>考生学号</th>
                                             <th>名称</th>
                                             <th>创建时间</th>
											 <th>状态</th>
											 <th>类型</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list }" var="paper">
	                                        <tr class="gradeX">
	                                        	 <th>${paper.paperRule.major.m_name }</th>
	                                        	 <th>${paper.paperRule.subject.sub_name }</th>
	                                        	 <th>${paper.studentRegister.sr_name }</th>
	                                        	 <th>${paper.studentRegister.sr_number }</th>
	                                             <th>${paper.pap_id }</th>
	                                             <jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
	                                             <jsp:setProperty property="time" name="date" value="${paper.create_time }"/>
	                                             <th><fmt:formatDate value="${date }" pattern="yyyy/MM/dd HH:mm:ss"/></th>
												 <th><%--0:刚生成, 1:已绑定 2:考试中, 3 已交卷, 4 已阅卷, 5,已废弃 --%>
												 	<c:if test="${paper.status eq 0 }">刚生成</c:if>
												 	<c:if test="${paper.status eq 1 }">已绑定</c:if>
												 	<c:if test="${paper.status eq 2 }">考试中</c:if>
												 	<c:if test="${paper.status eq 3 }">已交卷</c:if>
												 	<c:if test="${paper.status eq 4 }">已阅卷</c:if>
												 	<c:if test="${paper.status eq 5 }">已废弃</c:if>
												 </th>
												 <th>
												 	<c:if test="${paper.pap_kind eq 0 }">练习</c:if>
												 	<c:if test="${paper.pap_kind eq 1 }">考试</c:if>
												 </th>
	                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
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

     <script src="../style/js/sidebar_menu.js"></script>
	<script src="../style/js/plugins/layer/layer.min.js"></script>
	<script src="../style/js/contabs.min.js" type="text/javascript" ></script>
    <script src="../style/js/plugins/jeditable/jquery.jeditable.js"></script>
    <!-- Data Tables -->
    <script src="../style/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../style/js/plugins/dataTables/dataTables.bootstrap.js"></script>

   <script>
   
   $(document).ready(function () {
            $('.dataTables-example').dataTable();



        });


    </script>
</body>

</html>