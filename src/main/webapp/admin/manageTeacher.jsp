<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
     <link rel="stylesheet" href="../style/css/self.css" />
</head>

<body ng-app="exam">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
            	 <div class="manageTeacher">
			 <div class="container col-lg-12 " >
				   
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs" role="tablist">
				  	  <li class="nav-item  active">
				      <a class="nav-link active " data-toggle="tab" href="#addTeacher">添加老师</a>
				    </li>
				  	 <li class="nav-item  ">
				      <a class="nav-link    " data-toggle="tab" href="#editTeacher">编辑老师名单</a>
				    </li>
				  
				    
				    <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#importTeacher"> 导入 老师名单</a>
				    </li>
				      <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#emportTeacher"> 导出老师名单</a>
				    </li>
				  </ul>
				
				  <!-- Tab panes -->
				  <div class="tab-content" >
				    <div id="addTeacher" class="   active  tab-pane col-lg-12"><br>
				      <p> 
				       <div class="col-lg-12" ng-controller="teacher">
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
                                       <span class="sex">男</span><input type="radio" name="t_sex" value="1" checked="checked"> 
                                       <span class="sex">女</span><input type="radio" name="t_sex" value="0">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">能否登陆</label>
                                        <div class="col-sm-8">
                                       <span class="sex">是</span><input type="radio" name="status" value="1" checked="checked"> 
                                       <span class="sex">否</span><input type="radio" name="status" value="0">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">院系</label>
                                        <div class="col-sm-8">
                                           <select class="form-control m-b"  ng-model="selected" ng-options="m for m in tdepartment" name="academy.a_id" >
                                                 <option value="">-- 请选择 --</option>
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
				    <div id="editTeacher" class="container   tab-pane fade col-lg-12  active "><br>
				            
				             <div class="ibox-content">
				               <iframe class="J_iframe" name="iframe0" width="100%" height="900px" src="editTeacher.html" frameborder="0" data-id="hello.html" seamless></iframe>
                             </div> 
                             
				    </div>
				    <div id="importTeacher" class="container tab-pane fade col-lg-12"><br>
				      <p> 
				       <div class="col-lg-12" ng-controller="teacher">
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
                               <form class="form-horizontal m-t" id="importTeacherForm">
                               	  <div class="form-group">
                                     	  <label for="" class="col-sm-3 control-label">院系</label>
                                          <div class="col-sm-8">
                                          	   <select class="form-control m-b"  ng-model="selected" ng-options="m for m in tdepartment" >
                                                 <option value="">-- 请选择 --</option>
                                            </select>
                                          </div>
                                     </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">教师名单</label>
                                        <div class="col-sm-8">
                                            <input id=" upfile"   type="file"   >
                                        </div>
                                    </div>
                                   
                                      <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                           <a href="mangeStudent.html"> 下载教师名单模板</a>  <button class="btn btn-primary" type="submit"  >导入名单</button>
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
				      	<div class="col-lg-12 " ng-controller="teacher">
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
                                <form class="form-horizontal m-t" id="emportTeacherForm">
                                        	 <div class="form-group">
                                     	  <label for="" class="col-sm-3 control-label">院系</label>
                                          <div class="col-sm-8">
                                          	   <select class="form-control m-b"  ng-model="selected" ng-options="m for m in tdepartment" >
                                                 <option value="">-- 请选择 --</option>
                                            </select>
                                          </div>
                                     </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit"  class=" search">下载名单</button>
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
        </div>
     
<script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
    
    <!--<!--angularjs-->
        <!--引入angular插件-->
    <!--<script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
    <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
    <script src="../style/js/anjularJS/anjularjs/app.js"></script>
    <script src="../style/js/anjularJS/anjularjs/control.js"></script>-->
       <!-- DROPZONE -->
    <script src="../style/js/plugins/dropzone/dropzone.js"></script>
   
    <!-- jQuery Validation plugin javascript-->
    <script src="../style/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../style/js/plugins/validate/messages_zh.min.js"></script>

    <!-- iCheck -->
    <script src="../style/js/plugins/iCheck/icheck.min.js"></script>
     <script src="../style/js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="../style/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../style/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <!-- Page-Level Scripts -->
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
             $("#emportTeacherForm").validate({
                rules: {
                    userTable:'required'
                },
                messages: {
                  userTable:'请输入名单名称'
                }

            });
              $("#editTeacherForm").validate({
                rules: {
                    usernumber:'required'
                },
                messages: {
                  usernumber:'输入工号'
                }

            });
             $("#deleteTeacherForm").validate({
                rules: {
                   usernumber: "required",
                    username: {
                        required: true,
                        minlength: 2
                    }
                },
                messages: {
                    username:  "请输入删除的用户名",
                    usernumber:'输入工号',
                },
            });
        });
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
</script>

</body>

</html>