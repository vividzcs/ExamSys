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
   <link href="style/css/bootstrap.min.css" rel="stylesheet">
    <link href="style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <link href="style/css/animate.css" rel="stylesheet">
    <link href="style/css/style.css" rel="stylesheet">
    <link href="style/css/self.css" />
   <style>
   	.hello{
   		height: 600px;
   	}
 
   	.schoolInstroce{
     
     width:90%;
     margin: 0 auto;
   	   border: 1px solid #eee;
   	   	/*box-shadow: 1px -1px 10px #ccc;*/
   	   	text-align:center;
   	}
   	tbody tr:nth-child(2n+1){
   	background:#efe;
   	}
   		tbody tr{
   		height:30px;}
   	tbody tr:nth-child(2n){
   	background:#eee;
   	}
   	tbody tr:last-child{
   	text-align:left;
   	text-indent:2em;
   	height:50px;
   	}
   </style>

</head>
<body>
    
    <div class="hello row  border-bottom white-bg dashboard-header" style="height: 100%;" >
         <div class="schoolInstroce">
               <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content ">
                            	<h3>西北大学简介</h3>
                            	  <table class="table   table-striped table-bordered table-hover dataTables-example">
                                    <tbody>
                                         <tr>
                                             <td>学校编号</td>
											 <td>${school.sch_number }</td>
                                        </tr>
                                         <tr>
                                             <td>学校名称</td>
											 <td>${school.sch_name }</td>
                                        </tr>
                                         <tr>
                                             <td>学校地址</td>
											 <td>${school.sch_address }</td>
                                        </tr>
                                         <tr>
                                             <td>学校网站</td>
											 <td>${school.sch_website }</td>
                                        </tr>
                                        <tr>
                                             <td>学校院系数</td>
											 <td>${school.sch_aca_count }</td>
                                        </tr>
                                        <tr>
                                             <td>学校专业数</td>
											 <td>${school.sch_major_count }</td>
                                        </tr>
                                        <tr>
                                        	<td colspan="2">${school.sch_desc }</td>
                                        </tr>
                                </table>
                        </div>
               </div>
               
         </div>
</div>
</div>
      <!-- Mainly scripts -->
    <script src="style/js/jquery-2.1.1.min.js"></script>
    <script src="style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="./style/js/hplus.js?v=2.2.0"></script>
    <script src="./style/js/plugins/pace/pace.min.js"></script>
   <script src="./ style/js/plugins/iCheck/icheck.min.js"></script>
    <script src="./style/js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="./style/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="./style/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    
   <script>
       $(document).ready(function () {
            $('.dataTables-example').dataTable(); 

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            oTable.$('td').editable('../../../example_ajax.php', {
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