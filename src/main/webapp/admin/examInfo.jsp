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
    <link rel="stylesheet" href="../style/css/style.self.css" />
</head>

<body>
 
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
            	 <div class="emportStatus">
             <div class="wrapper wrapper-content animated fadeInRight">
                     <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>考试状态管理</h5>
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
                                            <th>考试科目</th>
                                            <th>监考老师</th>
                                            <th>阅卷老师</th>
                                            <th>考试时间</th>
                                            <th>考试人数</th>
                                            <th>注册人数</th>
                                            <th>实到人数</th>
                                            <th>考试状态</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${list }" var="info">
                                        <tr class="gradeX">
                                            <td>${info.subject.sub_name }</td>
                                            <td> ${info.guardianShip.guard_1.t_name }/${info.guardianShip.guard_2.t_name }</td>
                                            <td> ${info.guardianShip.read_1.t_name }/${info.guardianShip.read_2.t_name }/${info.guardianShip.read_3.t_name }  </td>
                                            <jsp:useBean id="start" class="java.util.Date" scope="request"></jsp:useBean>
                                            <jsp:useBean id="end" class="java.util.Date" scope="request"></jsp:useBean>
                                            <jsp:setProperty property="time" name="start" value="${info.rule.start_time }"/>
                                            <jsp:setProperty property="time" name="end" value="${info.rule.end_time }"/>
                                            <td> <fmt:formatDate value="${start }" pattern="yyyy/MM/dd HH:mm"/>-<fmt:formatDate value="${end }" pattern="yyyy/MM/dd HH:mm"/> </td>
                                            <td> ${info.exam_num_all } </td>
                                            <td> ${info.exam_num }  </td>
                                            <td> ${info.exam_num_reach }  </td>
                                            <td>
                                            	<c:if test="${info.status eq 0 }">未开始</c:if>
                                            	<c:if test="${info.status eq 1 }">考试中</c:if>
                                            	<c:if test="${info.status eq 2 }">已结束</c:if>
                                            </td>
                                            <td class="center"><a href="${pageContext.request.contextPath }/admin/admin_showExamInfoDetail.action?e_id=${info.e_id}">查看</a></td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    </tfoot>
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

<!-- Data Tables -->
    <script src="../style/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../style/js/plugins/dataTables/dataTables.bootstrap.js"></script>


    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>

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


        });

        function fnClickAddRow() {
            $('#editable').dataTable().fnAddData([
                "Custom row",
                "New row",
                "New row",
                "New row",
                "New row"]);

        }

    </script>
</body>

</html>