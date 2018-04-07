<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    	<style>
    			.pagination{
    		float: right;
    	}
    	</style>
    
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
				      <a class="nav-link active " data-toggle="tab" href="#editStudentLogin">查看考试人员</a>
				    </li>
				     <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#addStudent">添加考试学生</a>
				    </li>
				    <li class="nav-item ">
				      <a class="nav-link  " data-toggle="tab" href="#addTeacherLogin">添加监考人员</a>
				    </li>
				    <li class="nav-item">
				      <a class="nav-link" data-toggle="tab" href="#importPeople"> 导入人员</a>
				    </li>
				     
				  </ul>
				  <!-- Tab panes -->
				  <div class="tab-content">
			        <div id="editStudentLogin" class=" active container tab-pane  col-lg-12"><br>
				       <div class="ibox-content">
                                <table class="table   table-striped table-bordered table-hover dataTables-example">
                                    <thead>
                                        <tr>
                                             <th>专业</th>
											 <th>科目</th>
											 <th>学生姓名</th>
											 <th>学号</th>
											 <th>监考老师1 </th>
											 <th>监考老师2 </th>
											 <th>阅卷老师1 </th>
											 <th>阅卷老师2 </th>
											 <th>阅卷老师3 </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${list }" var="stu">
                                        <tr class="gradeX">
                                             <th>${stu.major.m_name }</th>
											 <th>${stu.subject.sub_name }</th>
											 <th>${stu.sr_name }</th>
											 <th>${stu.sr_number }</th>
											 <th>${stu.guardianShip.guard_1.t_name }</th>
											 <th>${stu.guardianShip.guard_2.t_name }</th>
											 <th>${stu.guardianShip.read_1.t_name }</th>
											 <th>${stu.guardianShip.read_2.t_name }</th>
											 <th>${stu.guardianShip.read_3.t_name }</th>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
			        </div>
				    <div id="addStudent" class="  container tab-pane  col-lg-12"><br>
                            <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加学生考试信息</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                 <form class="form-horizontal m-t" id="addStudent" action="${pageContext.request.contextPath }/admin/studentRegister_add.action" method="post">
                                    <!--<div class="form-group">
                                        <label class="col-sm-3 control-label">题库名称</label>
                                        <div class="col-sm-8">
                                            <input id="Bname" name="Bname" style="width:50%" class="form-control" type="text">
                                        </div>
                                    </div>-->
                                     <div class="form-group">
                                        <label class="col-sm-3 control-label">专业</label>
                                        <div class="col-sm-8">
                                            <select id="sProfess" name="major.m_id" class="form-control m-b" style="width: 50%" ng-model="selected" ng-options="m.profess for m in professes" ng-change="changeClassification(selected)">
                                                 <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">科目</label>
                                        <div class="col-sm-8">
                                             <select id="sSubject" name="subject.sub_id" class="form-control m-b" style="width: 50%" ng-model="selected2" ng-options="m for m in course" >
                                             	  <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">学号</label>
                                        <div class="col-sm-8">
                                            <input style="width: 50%;" id="usernumber" name="sr_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">确认添加</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
			       
				    <div id="addTeacherLogin" class="container tab-pane fade col-lg-12"><br>
				          <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 添加监考信息</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                 <form class="form-horizontal m-t" id="addTeacher" action="${pageContext.request.contextPath }/admin/guardianShip_add.action" method="post">
                                    <!--<div class="form-group">
                                        <label class="col-sm-3 control-label">题库名称</label>
                                        <div class="col-sm-8">
                                            <input id="Bname" name="Bname" style="width:50%" class="form-control" type="text">
                                        </div>
                                    </div>-->
                                     <div class="form-group">
                                        <label class="col-sm-3 control-label">专业</label>
                                        <div class="col-sm-8">
                                            <select id="tProfess" name="major.m_id" class="form-control m-b" style="width: 50%" ng-model="selected" ng-options="m.profess for m in professes" ng-change="changeClassification(selected)">
                                                 <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">科目</label>
                                        <div class="col-sm-8">
                                             <select id="tSubject" name="subject.sub_id" class="form-control m-b" style="width: 50%" ng-model="selected2" ng-options="m for m in course" >
                                             	  <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">监考1教师工号</label>
                                        <div class="col-sm-8">
                                            <input style="width: 50%;" id="tusernumber" name="guard_1.t_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">监考2教师工号</label>
                                        <div class="col-sm-8">
                                            <input style="width: 50%;" id="tusernumber" name="guard_2.t_number"" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">阅卷1教师工号</label>
                                        <div class="col-sm-8">
                                            <input style="width: 50%;" id="tusernumber" name="read_1.t_number"" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">阅卷2教师工号</label>
                                        <div class="col-sm-8">
                                            <input style="width: 50%;" id="tusernumber" name="read_2.t_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">阅卷3教师工号</label>
                                        <div class="col-sm-8">
                                            <input style="width: 50%;" id="tusernumber" name="read_3.t_number" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">确认添加</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                           
                        </div>
				    </div>
				    <div id="importPeople" class="  container tab-pane  col-lg-12"><br>
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5> 导入考试人员</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                             <div class="ibox-content">
                                <form class="form-horizontal m-t" id="signupForm" action="${pageContext.request.contextPath }/admin/admin_importPeopleRelated.action" method="post" enctype="multipart/form-data">
                                    <!--<div class="form-group">
                                        <label class="col-sm-3 control-label">题库名称</label>
                                        <div class="col-sm-8">
                                            <input id="Bname" name="Bname" style="width:50%" class="form-control" type="text">
                                        </div>
                                    </div>-->
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">上传考试学生名单</label>
                                        <div class="col-sm-8">
                                            <input id="upflieStu" type="file" name="upload" type="text" aria-required="true" aria-invalid="true" class="error">
                                        </div>
                                    </div>
                                     <div class="form-group">
                                        <label class="col-sm-3 control-label">上传监考阅卷教师名单</label>
                                        <div class="col-sm-8">
                                            <input id="upflieTea" type="file" name="upload" type="text" aria-required="true" aria-invalid="true" class="error">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">上传</button>
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">模板</label>
                                        <div class="col-sm-8">
                                            <a href="">下载考试学生模板</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="">下载监考阅卷模板</a>
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
             $("#addTeacher").validate({
                rules: {
                   tusernumber: "required",
                    tusername: {
                        required: true,
                        
                    }
                },
                messages: {
                   tusername:  "请输入教师名称",
                    tusernumber:'输入教师工号',
                },
            });
           
          
        });
          $(document).ready(function () {
            $('.dataTables-example').dataTable(); 

            /* Init DataTables */
            var oTable = $('#editable').dataTable();

            /* Apply the jEditable handlers to the table */
            /* oTable.$('td').editable('../../../example_ajax.php', {
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
            }); */
        });
          
          /**
           * 专业和科目的联动
           */
         var allData;
           $(function(){
         	  $.post('${pageContext.request.contextPath }/admin/admin_mesFS.action',{},function(data){
         		  allData = data;
       			var strProf = '<option value="">-- 请选择 --</option>';
       			for(var i in data) {
       				strProf += '<option value="' + i + '">'+ data[i].profess +'</option>'
       			}
       			console.log(11111111111111)
       			$("#sProfess").html(strProf);
       			$("#tProfess").html(strProf);
       			
       		},"json")
           })
           
           $("#sProfess").change(function(){
         	  var id = $(this).val();
         	  if(id != '') {
         		  var sub = $("#sSubject");  
         		  var strSub = '<option value="">-- 请选择 --</option>';
         		  var subjects = allData[id];
         		  for(var i in subjects.subject) {
         			strSub += '<option value="' + i + '">'+ subjects.subject[i] +'</option>'
         			}
         		sub.html(strSub);
         	  }
         	  
           });
           $("#tProfess").change(function(){
          	  var id = $(this).val();
          	  if(id != '') {
          		  var sub = $("#tSubject");  
          		  var strSub = '<option value="">-- 请选择 --</option>';
          		  var subjects = allData[id];
          		  for(var i in subjects.subject) {
          			strSub += '<option value="' + i + '">'+ subjects.subject[i] +'</option>'
          			}
          		sub.html(strSub);
          	  }
          	  
            });
          
     //改变url中的参数
//   $(".nav-link").on("click",function(){
//   	var test= window.location.href;
//   	var index = test.indexOf("?");
//      window.location.href=window.location.href.substring(0,index)+"#"+this.href;
//     console.log(window.location.href);
//   })
        </script>
   
</body>

</html>