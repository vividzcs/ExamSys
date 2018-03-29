<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="exam">
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
	<body ng-controller="department">
		            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
		         <div id="addDepartment" class="container tab-pane active"><br>
				       <div class="col-lg-10" >
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加科目</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="addProfessForm" action="${pageContext.request.contextPath }/admin/subject_update.action" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">专业</label>
                                        <input type="hidden" name="sub_id" value="${subject.sub_id }">
                                        <div class="col-sm-8">
                                             <select id="profess" name="major.m_id" class="form-control m-b" ng-model="selected2" ng-options="m for m in profess" >
                                             	  <option value="">-- 请选择 --</option>
                                             	  <c:forEach items="${list }" var="major">
                                             	  	<option value="${major.m_id }" <c:if test="${major.m_id eq subject.major.m_id }">selected="selected"</c:if> >${major.m_name }</option>
                                             	  </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                       <div class="form-group">
                                        <label class="col-sm-3 control-label">科目编号</label>
                                        <div class="col-sm-8">
                                            <input id="professCnumber" name="sub_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname" value="${subject.sub_number }">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">科目名称</label>
                                        <div class="col-sm-8">
                                            <input id="professCname" name="sub_name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname" value="${subject.sub_name }">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary addStudet" type="submit"> 确认添加</button>&nbsp;&nbsp;&nbsp;<button class="btn btn-primary addStudet" type="submit" ><a href="manageCourse.html" style="color: white;">返回列表</a></button>
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
       <!--  <script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src=" ../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
   <script src= "../style/js/anjularJS/anjularjs/app.js"></script>
   <script src= "../style/js/anjularJS/anjularjs/control.js"></script> -->
  
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
            $("#addProfessForm").validate({
                rules: {
                    professCnumber:  "required",
                    professCname:"required",
                },
                messages: {
                  
                    professCnumber:'输入科目编号',
                    professCname:"输入科目名称",
                 
                }

            });
        
        });
         
        </script>
	</body>
</html>
