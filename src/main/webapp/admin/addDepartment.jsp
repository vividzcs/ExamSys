<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>学生考试系统</title>
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
	<body>
		            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
		         <div id="addDepartment" class="container tab-pane active"><br>
				       <div class="col-lg-10" >
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加院系</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="addDepartmentForm" action="${pageContext.request.contextPath }/admin/department_add.action" method="post">
                                	 <div class="form-group">
                                        <label class="col-sm-3 control-label">院系编号</label>
                                        <div class="col-sm-8">
                                            <input id="departmentnumber" name="a_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">院系名称</label>
                                        <div class="col-sm-8">
                                            <input id="departmentname" name="a_name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary addStudet" type="submit"> 确认添加</button>&nbsp;&nbsp;&nbsp;<button class="btn btn-primary addStudet" type="button" ><a href="manageDepartment.jsp" style="color: white;">返回列表</a></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
				    </div>
				    </div>
				    <script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
    
       <!-- DROPZONE -->
    <script src="../style/js/plugins/dropzone/dropzone.js"></script>
   
    <!-- jQuery Validation plugin javascript-->
    <script src="../style/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../style/js/plugins/validate/messages_zh.min.js"></script>

    <!-- iCheck -->
    <script src="../style/js/plugins/iCheck/icheck.min.js"></script>
    <script>
      $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"


        });

         //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            $("#addDepartmentForm").validate({
                rules: {
                    departmentnumber:  "required",
                    departmentname: "required",
                },
                messages: {
                  
                    departmentnumber:'输入院系编号',
                    departmentname:'输入院系名称'
                }

            });
        
        });
        </script>
	</body>
</html>
