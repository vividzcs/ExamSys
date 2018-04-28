<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                             <th>学号</th>
                                               <th>姓名</th>
											 <th>状态</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${srList }" var="sr">
                                    <tbody>
                                        <tr class="gradeX">
                                             <th>${sr.sr_number }</th>
                                              <th>${sr.sr_name }</th>
											 <th><%--0:未注册, 1:已注册未生成试卷, 2:已注册已生成试卷  3:考试中, 4: 已考完, 5:已阅卷 --%>
											 	<c:if test="${sr.status eq 0 }">未注册</c:if>
											 	<c:if test="${sr.status eq 1 }">已注册未生成试卷</c:if>
											 	<c:if test="${sr.status eq 2 }">已注册已生成试卷</c:if>
											 	<c:if test="${sr.status eq 3 }">考试中</c:if>
											 	<c:if test="${sr.status eq 4 }">已考完</c:if>
											 	<c:if test="${sr.status eq 5 }">已阅卷</c:if>
											 </th>
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