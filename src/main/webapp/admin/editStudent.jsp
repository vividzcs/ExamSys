<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		      <div class="col-lg-12" ng-controller="addStudent">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加学生基本信息</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="addStudentForm" action="${pageContext.request.contextPath }/admin/student_updateA.action" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">姓名</label>
                                        <div class="col-sm-8">
                                        	<input type="hidden" name="s_id" value="${stu.s_id }">
                                            <input id="username" name="s_name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname" value="${stu.s_name }">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">学号</label>
                                        <div class="col-sm-8">
                                            <input id="usernumber" name="s_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" value="${stu.s_number }">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="s_pass" class="form-control" type="text" autocomplete="off" class="spassword" require value="${stu.s_pass }">
                                        </div>
                                    </div>
                                   
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">性别</label>
                                        <div class="col-sm-8">
                                        
                                       <span class="sex">男</span>&nbsp;<input <c:if test="${stu.s_sex eq 1 }">checked="checked"</c:if> type="radio" name="s_sex" value="1"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       <span class="sex">女</span>&nbsp;<input  <c:if test="${stu.s_sex eq 0 }">checked="checked"</c:if> type="radio" name="s_sex" value="0">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                            <select id="department" class="form-control m-b"  ng-model="selected" ng-options="m.department for m in departments" name="academy.a_id">
                                                 <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">专业</label>
                                        <div class="col-sm-8">
                                             <select id="profess" class="form-control m-b" ng-model="selected2" ng-options="m for m in profess" name="major.m_id">
                                             	  <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary addStudet" type="submit"> 确认添加</button>
                                        </div>
                                    </div>
                                </form>
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
            $("#addStudentForm").validate({
                rules: {
                    username: {
                        required: true,
                    },
                      usernumber: "required",
                    password: {
                        required: true,
                        minlength: 5
                    },
                    
                },
                messages: {
                    username: "请输入您的用户名",
                    usernumber:'输入学号',
                    password:   "请输入您的密码"
                    
                }
            });
        });
         
        /**
         * 院系和专业的联动
         */
       var allData;
        var academy_id = ${stu.academy.a_id}
        var major_id = ${stu.major.m_id}
         $(function(){
       	  $.post('${pageContext.request.contextPath }/admin/admin_mesDF.action',{},function(data){
       		  allData = data;
       		  //院系
     			var strDept = '<option value="">-- 请选择 --</option>';
     			for(var i in data) {
     				if(i == academy_id)
     					strDept += '<option value="' + i + '" selected="selected">'+ data[i].department +'</option>'
     				else
     					strDept += '<option value="' + i + '">'+ data[i].department +'</option>'	
     			}
     			//专业
     			var prof = $("#profess");  
         		  var strProf = '<option value="">-- 请选择 --</option>';
         		  var professes = allData[academy_id];
         		  for(var i in professes.profess) {
         			  if(i == major_id)
         			  	strProf += '<option value="' + i + '" selected="selected">'+ professes.profess[i] +'</option>'
         			  else
         				strProf += '<option value="' + i + '">'+ professes.profess[i] +'</option>'
         			}
         		  prof.html(strProf);
     			$("#department").html(strDept);
     			
     		},"json")
         })
         
         $("#department").change(function(){
       	  var id = $(this).val();
       	  if(id != '') {
       		  var prof = $("#profess");  
       		  var strProf = '<option value="">-- 请选择 --</option>';
       		  var professes = allData[id];
       		  for(var i in professes.profess) {
       			  if(i == major_id)
       			  	strProf += '<option value="' + i + '" selected="selected">'+ professes.profess[i] +'</option>'
       			  else
       				strProf += '<option value="' + i + '">'+ professes.profess[i] +'</option>'
       			}
       		  prof.html(strProf);
       	  }
       	  
         });
</script>
	</body>
</html>