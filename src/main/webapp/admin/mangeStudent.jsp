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

<body ng-app="exam">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
             <div class="manageStudent">
				        	 	
			 <div class="container " style="width: 100%;">
				   
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs " role="tablist">
				    <li class="nav-item active">
				      <a class="nav-link  active" data-toggle="tab" href="#addStudent">添加学生</a>
				    </li>
				    <li class="nav-item ">
				      <a class="nav-link  " data-toggle="tab" href="#editStudent">编辑学生</a>
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
				    <div id="addStudent" class=" active container tab-pane  col-lg-12"><br>
				      <p> 
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
				    </div>
			     <div id="importStudent" class=" container tab-pane  col-lg-12"><br>
				      <p> 
				       <div class="col-lg-12" ng-controller="addStudent">
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
                                <form class="form-horizontal m-t" id="importStudentForm">
                                      	  
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
                                        <label class="col-sm-3 control-label">学生名单</label>
                                        <div class="col-sm-8">
                                            <input id=" upfile"   type="file"   >
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                           <a href="E:/www/javaDemo/ExamSys_html/modellist/教师名单模板.xlsx"> 下载学生模板</a>  <button class="btn btn-primary" type="submit"  >导入名单</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
                   </p>
				    </div>
				      
			     <div id="editStudent" class=" container tab-pane  col-lg-12"><br>
				        <iframe class="J_iframe" name="iframe0" width="100%" height="900px" src="editStudent.html" frameborder="0" data-id="hello.html" seamless></iframe>
				    </div>
				    <div id="emportStudent" class="container tab-pane fade col-lg-12"><br>
				      <p>
				      	<div class="col-lg-12" ng-controller="addStudent">
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
                                <form class="form-horizontal m-t" id="emportStudentForm">
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
</script>

</body>

</html>