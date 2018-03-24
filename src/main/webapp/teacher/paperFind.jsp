<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

</head>

<body>
            

			<div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
			                       <div class="ibox-content">

                                <table class="table table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                        <tr>
                                             <th>试卷编号</th>
											 <th>试卷名称</th>
											 <th>答题人</th>
											 <th>提交时间 </th>
											 <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="gradeX">
                                            <td>001</td>
                                            <td>Internet Explorer 4.0
                                            </td>
                                            <td>张三</td>
                                            <td class="center">2018-1-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeC">
                                            <td>002</td>
                                            <td>Internet Explorer 5.0
                                            </td>
                                            <td>李四</td>
                                            <td class="center">2018-1-1</td>
											<td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
										
                                        <tr class="gradeA">
                                            <td>003</td>
                                            <td>Internet Explorer 5.5
                                            </td>
                                            <td>张三</td>
                                            <td class="center">2018-1-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>004</td>
                                            <td>Internet Explorer 6
                                            </td>
                                            <td>李四</td>
                                            <td class="center">2018-1-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>005</td>
                                            <td>Internet Explorer 7</td>
                                            <td>Win XP SP2+</td>
                                            <td class="center">2018-1-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>006</td>
                                            <td>AOL browser (AOL desktop)</td>
                                            <td>Win XP</td>
                                            <td class="center">2018-1-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>007</td>
                                            <td>Firefox 1.0</td>
                                            <td>Win 98+ / OSX.2+</td>
                                            <td class="center">2018-1-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>008</td>
                                            <td>Firefox 1.5</td>
                                            <td>Win 98+ / OSX.2+</td>
                                            <td class="center">2018-1-1</td>
                                           <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>009</td>
                                            <td> 2.0</td>
                                            <td>Win 98+ / OSX.2+</td>
                                            <td class="center">2018-2-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>010</td>
                                            <td>Firefox 3.0</td>
                                            <td>Win 2k+ / OSX.3+</td>
                                            <td class="center">2018-2-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>011</td>
                                            <td>Camino 1.0</td>
                                            <td>OSX.2+</td>
                                            <td class="center">2018-2-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>012</td>
                                            <td>Camino 1.5</td>
                                            <td>OSX.3+</td>
                                            <td class="center">2018-2-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>013</td>
                                            <td>Netscape 7.2</td>
                                            <td>Win 95+ / Mac OS 8.6-9.2</td>
                                            <td class="center">2018-2-1</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>014</td>
                                            <td>Netscape Browser 8</td>
                                            <td>Win 98SE+</td>
                                            <td class="center">2017-12-31</td>
                                           <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>015</td>
                                            <td>Netscape Navigator 9</td>
                                            <td>Win 98+ / OSX.2+</td>
                                            <td class="center">2017-12-31</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>016</td>
                                            <td>Mozilla 1.0</td>
                                            <td>Win 95+ / OSX.1+</td>
                                            <td class="center">2017-12-31</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>017</td>
                                            <td>Mozilla 1.1</td>
                                            <td>Win 95+ / OSX.1+</td>
                                            <td class="center">2017-12-31</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>018</td>
                                            <td>Mozilla 1.2</td>
                                            <td>Win 95+ / OSX.1+</td>
                                            <td class="center">2018-2-2</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>019</td>
                                            <td>Mozilla 1.3</td>
                                            <td>Win 95+ / OSX.1+</td>
                                            <td class="center">2018-2-2</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                        <tr class="gradeA">
                                            <td>020</td>
                                            <td>Mozilla 1.4</td>
                                            <td>Win 95+ / OSX.1+</td>
                                            <td class="center">2018-2-2</td>
                                            <td class="center"><a href="">下载</a>&nbsp<a href="">预览</a></td>
                                        </tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>试卷编号</th>
											 <th>试卷名称</th>
											 <th>答题人</th>
											 <th>提交时间 </th>
											 <th>操作</th>
                                        </tr>
                                    </tfoot>
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


        });

	</script>

</body>

</html>