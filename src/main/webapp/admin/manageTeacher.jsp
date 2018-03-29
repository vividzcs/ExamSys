<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html ng-app="exam">

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

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
    <!--<link rel="stylesheet" type="text/css" href="../style/css/self.css"/>-->

</head>

<body ng-controller="department">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
			 <div class="container " style="width: 100%;">
				   
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs " role="tablist">
				  	<li class="nav-item active">
				      <a class="nav-link active " data-toggle="tab" href="#editTeacher">编辑老师</a>
				    </li>
				    <li class="nav-item ">
				      <a class="nav-link  " data-toggle="tab" href="#addTeacher">添加老师</a>
				    </li>
				    <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#importTeacher"> 导入老师名单</a>
				    </li>
				      <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#emportTeacher"> 导出老师名单</a>
				    </li>
				  </ul>
				  <!-- Tab panes -->
				  <div class="tab-content">
				  	  
			        <div id="editTeacher" class=" active container tab-pane  col-lg-12"><br>
				        <div class="ibox-content">
                                <table class="table   table-striped table-bordered table-hover dataTables-example">
                                      <thead>
                                        <tr>
                                             <th>姓名</th>
											 <th>工号</th>
											 <th>性别</th>
											 <th>密码</th>
											 <th>院系 </th>
											  <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${tea_list }" var="teacher">
                                        <tr class="gradeX">
                                             <th>${teacher.t_name }</th>
											 <th>${teacher.t_number }</th>
											 <th>
											 	<c:if test="${teacher.t_sex eq 1 }">男</c:if>
											 	<c:if test="${teacher.t_sex eq 0 }">女</c:if>
											 </th>
											 <th>${teacher.t_pass }</th>
											 <th>${teacher.academy.a_name}</th>
                                            <td class="center"><a href="${pageContext.request.contextPath }/admin/teacher_delete?t_id=${teacher.t_id}" class="delete">删除</a>&nbsp<a href="${pageContext.request.contextPath }/admin/teacher_editA.action?t_id=${teacher.t_id}" class="edit">修改</a></td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>  </div>
                            
				    <div id="addTeacher" class="  container tab-pane  col-lg-12"><br>
				     <p> 
				       <div class="col-lg-12"  >
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加老师基本信息</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="addTeacherForm" action="${pageContext.request.contextPath }/admin/teacher_add.action" method="post">
                                     
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">姓名</label>
                                        <div class="col-sm-8">
                                            <input id="username" name="t_name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error tname">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">工号</label>
                                        <div class="col-sm-8">
                                            <input id="usernumber" name="t_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error tnumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-8">
                                            <input id="password" name="t_pass" class="form-control" type="password" class="tpassword">
                                        </div>
                                    </div>
                                   
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">性别</label>
                                        <div class="col-sm-8">
                                       <span class="sex">男</span><input   type="radio" name="t_sex" value="1" checked="checked"> 
                                       <span class="sex">女</span><input   type="radio" name="t_sex" value="0">
                                        </div>
                                    </div>
                                     <div class="form-group"  >
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                            <select class="form-control m-b"  ng-model="selected" ng-options="m.department for m in departments" name="academy.a_id" >
                                                 <option value="0">-- 请选择 --</option>
                                                 <c:forEach items="${aca_list }" var="academy">
                                                 <option value="${academy.a_id }">${academy.a_name }</option>
                                                 </c:forEach>
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
                   
				      </p>
				    </div>
			       <div id="importTeacher" class="  container tab-pane  col-lg-12"><br>
                     <p> 
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 上传excel的老师名单格式</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                               <form class="form-horizontal m-t" id="importTeacherForm" method="post" action="${pageContext.request.contextPath }/admin/teacher_importTeacher.action" enctype="multipart/form-data">
                               	  <div class="form-group" ng-controller="department">
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">教师名单</label>
                                        <div class="col-sm-8">
                                            <input id="upfile" type="file" name="file_upload"  >
                                        </div>
                                    </div>
                                   
                                      <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                           <a href="mangeStudent.html"> 下载教师名单模板</a>  <button class="btn btn-primary" type="submit">导入名单</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
                   </p>
				     </div>
				    <div id="emportTeacher" class="container tab-pane fade col-lg-12"><br>
				         <p>
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                            	 <h5> 导出老师名单</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="emportTeacherForm" action="${pageContext.request.contextPath }/admin/teacher_downloadTeacher.action" method="post">
                                        <div class="form-group" ng-controller="department">
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                            <select class="form-control m-b"  ng-model="selected" ng-options="m.department for m in departments" id="a_id" name="a_id">
                                                 <option value="0">-- 请选择 --</option>
                                                 <c:forEach items="${aca_list }" var="academy">
                                                 <option value="${academy.a_id }">${academy.a_name }</option>
                                                 </c:forEach>
                                            </select>
                                        </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit"   class=" search">下载名单</button>
                                            <!-- onclick="downloadTeacher()" -->
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
				      </p>
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
    <script>

         //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

            // validate signup form on keyup and submit
            $("#addTeacherForm").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 2
                    },
                      usernumber: "required",
                    password: {
                        required: true,
                        minlength: 5
                    },
                    
                },
                messages: {
                    username: {
                        required: "请输入老师用户名",
                        minlength: "用户名必须两个字符以上"
                    },
                    usernumber:'输入学号',
                    password: {
                        required: "请输入老师密码",
                        minlength: "密码必须5个字符以上"
                    },
                }

            });
              
        });
         
        $(document).ready(function () {
            $('.dataTables-example').dataTable();
            
        })
        
        function downloadTeacher(){
        	var a_id = $("#a_id").val();
        	var data = {a_id:a_id};
        	$.post('${pageContext.request.contextPath}/teacher_downloadTeacher.action',data,function(data){
    			//console.log(data)
    		},"json")
        }
        </script>
   <!--<script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
    <script src="../style/js/anjularJS/anjularjs/app.js"></script>
   <script src="../style/js/anjularJS/anjularjs/control.js"></script> -->
  
</body>

</html>