<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/head.jsp"></jsp:include>
    	<style>
    			.pagination{
    		float: right;
    	}
    	</style>
    	
<body ng-controller="department">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
			 <div class="container " style="width: 100%;">
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs " role="tablist">
				  	<li class="nav-item active">
				      <a class="nav-link active " data-toggle="tab" href="#editStudent">编辑学生</a>
				    </li>
				    <li class="nav-item ">
				      <a class="nav-link  " data-toggle="tab" href="#addStudent">添加学生</a>
				    </li>
				    <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#importStudent"> 导入学生名单</a>
				    </li>
				      <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#emportStudent"> 导出学生名单</a>
				    </li>
				  </ul>
				  <!-- Tab panes -->
				  <div class="tab-content">
			        <div id="editStudent" class=" active container tab-pane  col-lg-12"><br>
				       <div class="ibox-content">
                                <table class="table   table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                        <tr>
                                             <th>姓名</th>
											 <th>学号</th>
											 <th>性别</th>
											 <th>院系 </th>
											 <th>专业</th>
											  <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${s_list }"  var="stu">
                                        <tr class="gradeX">
                                             <th>${stu.s_name }</th>
											 <th>${stu.s_number }</th>
											 <th>
											 	<c:if test="${stu.s_sex eq 1 }">男</c:if>
											 	<c:if test="${stu.s_sex eq 0 }">女</c:if>
											 </th>
											 <th>${stu.academy.a_name }</th>
											 <th>${stu.major.m_name}</th>
                                            <td class="center"><a href="${pageContext.request.contextPath }/admin/student_delete.action?s_id=${stu.s_id}" class="delete">删除</a>&nbsp;<a href="${pageContext.request.contextPath }/admin/student_edit.action?s_id=${stu.s_id}" class="edit">修改</a></td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
			        </div>
				    <div id="addStudent" class="  container tab-pane  col-lg-12"><br>
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
                                <form class="form-horizontal m-t" id="addStudentForm">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">姓名</label>
                                        <div class="col-sm-8">
                                            <input id="username" name="username" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error sname">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">学号</label>
                                        <div class="col-sm-8">
                                            <input id="usernumber" name="usernumber" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="password" class="form-control" type="password" class="spassword" require>
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">性别</label>
                                        <div class="col-sm-8">
                                       <span class="sex">男</span>&nbsp;<input  checked="checked" type="radio" name="sex"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       <span class="sex">女</span>&nbsp;<input   type="radio" name="sex">
                                        </div>
                                    </div>
                                       <div class="form-group">
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                            <select class="form-control m-b"  ng-model="selected" ng-options="m.department for m in departments" ng-change="changeClassification(selected)">
                                                 <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">专业</label>
                                        <div class="col-sm-8">
                                             <select class="form-control m-b" ng-model="selected2" ng-options="m for m in profess" >
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
			       <div id="importStudent" class="  container tab-pane  col-lg-12"><br>
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 上传excel的学生名单格式</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="importStudentForm" action="${pageContext.request.contextPath }/admin/student_importStudent.action" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">学生名单</label>
                                        <div class="col-sm-8">
                                            <input id="upfile"   type="file" name="file_upload"  >
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                           <a href="mangeStudent.html"> 下载学生模板</a>  <button class="btn btn-primary" type="submit"  >导入名单</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
				     </div>
				    <div id="emportStudent" class="container tab-pane fade col-lg-12"><br>
				         <p>
                              <div class="ibox float-e-margins">
                            <div class="ibox-title">
                            	  <h5>  导出学生名单</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="emportStudentForm" action="${pageContext.request.contextPath }/admin/student_exportStudent.action" method="post">
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit"  class=" search">下载名单</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
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
         <script src="../style/js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="../style/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../style/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    
       <script src="../style/js/plugins/iCheck/icheck.min.js"></script>
    <script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
   <script src="../style/js/anjularJS/anjularjs/app.js"></script>
   <script src="../style/js/anjularJS/anjularjs/control.js"></script>
  
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
             $("#emportStudentForm").validate({
                rules: {
                    userTable:'required'
                },
                messages: {
                  userTable:'请输入名单名称'
                }

            });
             $("#deleteStudentForm").validate({
                rules: {
                   usernumber: "required",
                    username: {
                        required: true,
                        minlength: 2
                    }
                },
                messages: {
                    username:  "请输入删除的用户名",
                    usernumber:'输入学号',
                },
            });
             $("#deleteStudentForm").validate({
                rules: {
                   usernumber: "required",
                    username: {
                        required: true,
                        minlength: 2
                    }
                },
                messages: {
                    username:  "请输入删除的用户名",
                    usernumber:'输入学号',
                },
            });
              $("#editStudentForm").validate({
                rules: {
                    usernumber:'required'
                },
                messages: {
                  usernumber:'输入学号'
                }

            });
        });
          $(document).ready(function () {
            $('.dataTables-example').dataTable(); 

        });
        
          $(function(){
        	  $.post('${pageContext.request.contextPath }/admin/admin_mes.action',{},function(data){
      			console.log(data)
      			
      		},"json")
          })
        
   </script>     
        </body>

</html>