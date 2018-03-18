<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">

    <title>登录页面</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link href="style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="style/font-awesome/style/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <link href="style/css/animate.css" rel="stylesheet">
    <link href="style/css/style.css?v=2.2.0" rel="stylesheet">
   <style>
   body{
   	background: url(style/img/bg .jpg) no-repeat center ;
   	background-position: 500px 200px;
   }
   .login ul {
    margin:35% auto;     	
   }
     .login ul li {
     	float: left;
     	list-style: none;
     	width: 150px;
     	height: 150px;
     	background:#aaa;
     	border-radius: 20%;
     	text-align: center;
     	font: 30px/150px "微软雅黑";
     	margin-left: 20px;
     	box-shadow: 2px 2px 10px #444444;
     }
   </style>
</head>

<body >
<div class="  loginscreen  animated fadeInDown">
	<div class="col-lg-3"></div>
 <div class="container  login col-lg-6">
    <div class="list-group m-t">
  	   <ul> 
  	    <li class="col-sm-12" data-toggle="modal" data-target="#studentModal"> <a href="#" >学生</a></li>
  	    <li class="col-sm-12" data-toggle="modal" data-target="#teacherModal"> <a href="#" >教师</a></li>
  	    <li class="col-sm-12" data-toggle="modal" data-target="#adminModal">  <a href="#">管理员</a></li>
  	    </ul>
    </div>
    <div class="col-lg-3"></div>
      <!-- 模态框 -->
 </div>
</div>
 <!-- 模态框 -->
 <div class="modal fade" id="studentModal">
    <div class="modal-dialog">
      <div class="modal-content">
   
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title">学生登录系统</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        <!-- 模态框主体 -->
        </div>
        <div class="modal-body">
           <form class="form-horizontal" id="studentLogin" action="" method="post">
                <div class="form-group">
                  <label class="col-lg-3 control-label">用户名：</label>
                     <div class="col-lg-8">
                     <input type="username" placeholder="用户名" class="form-control" name="s_number" id="s_number">
                  </div>
               </div>
               <div class="form-group">
                <label class="col-lg-3 control-label">密码：</label>
                   <div class="col-lg-8">
                       <input type="password" placeholder="密码" class="form-control" name="s_pass" id="s_pass">
                          </div>
                      </div>
                      <div class="form-group">
                     <div class="col-lg-offset-3 col-lg-8">
                       <button class="btn btn-primary" type="button" id="s_login">登 录</button>
                        </div>
                    </div>
               </form>
           </div>
                 </div>
        <!-- 模态框底部 -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        </div>
   
      </div>
    </div>
  </div>
 
  <!-- 模态框 -->
  <div class="modal fade" id="teacherModal">
    <div class="modal-dialog">
      <div class="modal-content">
   
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title">教师登录系统</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        <!-- 模态框主体 -->
        </div>
        <div class="modal-body">
           <form class="form-horizontal" id="teacherLogin" action="" method="post">
                <div class="form-group">
                  <label class="col-lg-3 control-label">用户名：</label>
                     <div class="col-lg-8">
                     <input type="username" placeholder="用户名" class="form-control" name="t_number" id="t_number">
                  </div>
               </div>
               <div class="form-group">
                <label class="col-lg-3 control-label">密码：</label>
                   <div class="col-lg-8">
                       <input type="password" placeholder="密码" class="form-control" name="t_pass" id="t_pass">
                          </div>
                      </div>
                      <div class="form-group">
                     <div class="col-lg-offset-3 col-lg-8">
                       <button class="btn btn-primary" type="button" id="t_login">登 录</button>
                        </div>
                    </div>
               </form>
           </div>
                 </div>
        <!-- 模态框底部 -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        </div>
   
      </div>
    </div>
  </div>
  
  <!-- 模态框 -->
  <div class="modal fade" id="adminModal">
    <div class="modal-dialog">
      <div class="modal-content">
   
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title">管理员登录系统</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        <!-- 模态框主体 -->
        </div>
        <div class="modal-body">
           <form class="form-horizontal" id='adminLogin'>
                <div class="form-group">
                  <label class="col-lg-3 control-label">用户名：</label>
                     <div class="col-lg-8">
                     <input type="username" placeholder="用户名" class="form-control" name="ad_number" id="ad_number">
                  </div>
               </div>
               <div class="form-group">
                <label class="col-lg-3 control-label">密码：</label>
                   <div class="col-lg-8">
                       <input type="password" placeholder="密码" class="form-control" name="ad_pass" id="ad_pass">
                          </div>
                      </div>
                      <div class="form-group">
                     <div class="col-lg-offset-3 col-lg-8">
                      <button class="btn btn-primary" type="button" id="ad_login">登 录</button>
                        </div>
                    </div>
               </form>
           </div>
                </div>
        <!-- 模态框底部 -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        </div>
   
      </div>
    </div>
  </div>
</div>
    <!-- Mainly scripts -->
    <script src="style/js/jquery-2.1.1.min.js"></script>
    <script src="style/js/bootstrap.min.js?v=3.4.0"></script>
</body>
<script>
	$("#s_login").click(function() {
		var s_number = $("#s_number").val();
		var s_pass = $("#s_pass").val();
		var data = {s_number:s_number,s_pass:s_pass};
		$.post('${pageContext.request.contextPath}/student_login.action',data,function(data){
			//console.log(data)
			if(data.status == 1) {
				//登录成功
				$(window).attr("location","${pageContext.request.contextPath}/student/");
			} else {
				//展示错误数据
			}
		},"json")
		
	})
	
	$("#t_login").click(function() {
		var t_number = $("#t_number").val();
		var t_pass = $("#t_pass").val();
		var data = {t_number:t_number,t_pass:t_pass};
		$.post('${pageContext.request.contextPath}/teacher_login.action',data,function(data){
			//console.log(data)
			if(data.status == 1) {
				//登录成功
				$(window).attr("location","${pageContext.request.contextPath}/teacher/");
			} else {
				//展示错误数据
			}
		},"json")
		
	})
	
	$("#ad_login").click(function() {
		var ad_number = $("#ad_number").val();
		var ad_pass = $("#ad_pass").val();
		var data = {ad_number:ad_number,ad_pass:ad_pass};
		$.post('${pageContext.request.contextPath}/admin_login.action',data,function(data){
			//console.log(data)
			if(data.status == 1) {
				//登录成功
				$(window).attr("location","${pageContext.request.contextPath}/admin/");
			} else {
				//展示错误数据
			}
		},"json")
		
	})
</script>
</html>
