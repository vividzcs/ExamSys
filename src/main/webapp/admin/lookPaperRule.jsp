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

   <!-- <title>素材火www.sucaihuo.com - 主页</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术"> -->


    <link href="../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
     <style type="text/css">
     		.pagination{
    		float: right;
    	}
     </style>
</head>

<body>
            

			<div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
			                       <div class="ibox-content">

                                <table class="table table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                        <tr>
                                        <th>规则名</th>
                                             <th>专业</th>
                                               <th>科目</th>
											 <th>难度</th>
											 <th>总分</th>
											 <th>开始时间</th>
											 <th>结束时间</th>
											  <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${list }" var="rule">
                                        <tr class="gradeX">
                                        <th>${rule.p_name }</th>
                                             <th>${rule.major.m_name }</th>
                                              <th>${rule.subject.sub_name }</th>
											 <th>
											 	<c:if test="${rule.degree eq 0 }">易</c:if>
											 	<c:if test="${rule.degree eq 1 }">中</c:if>
											 	<c:if test="${rule.degree eq 2 }">难</c:if>
											 </th>
											 <th>${rule.full_score}</th>
											 <jsp:useBean id="start" class="java.util.Date"></jsp:useBean>
											 <jsp:setProperty property="time" name="start" value="${rule.start_time }"/>
											 <th><fmt:formatDate value="${start }" pattern="yyyy/MM/dd HH:mm"/></th>
											 <jsp:useBean id="end" class="java.util.Date"></jsp:useBean>
											 <jsp:setProperty property="time" name="end" value="${rule.end_time }"/>
											 <th><fmt:formatDate value="${end }" pattern="yyyy/MM/dd HH:mm"/></th>
                                            <td class="center"><a href="${pageContext.request.contextPath }/admin/paperrule_delete.action?p_id=${rule.p_id}" class="delete">删除</a>&nbsp<a href="${pageContext.request.contextPath }/admin/paperrule_edit.action?p_id=${rule.p_id}" >修改</a>&nbsp<a href="${pageContext.request.contextPath }/admin/paperrule_detail.action?p_id=${rule.p_id}" >详细</a></td>
                                        </tr>
                                        </c:forEach> 
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
			
			
           
   <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>

	<!-- Mainly scripts -->
	<script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <script src="../style/js/plugins/jeditable/jquery.jeditable.js"></script>
    <!-- Data Tables -->
    <script src="../style/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../style/js/plugins/dataTables/dataTables.bootstrap.js"></script>

   
    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
            $('.dataTables-example').dataTable(); 

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            oTable.$('td').editable('../../example_ajax.php', {
                "callback": function (sValue, y) {
                    var aPos = oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue, aPos[0], aPos[1]);
                },
                "submitdata": function (value, settings) {
                    return {
                        "row_id": this.parentNode.getAttribute('id'),
                        "column": oTable.fnGetPosition(this)[2]
                    };
                },

                "width": "90%",
                "height": "100%"
            });
         $("")

        });

	</script>

</body>

</html>