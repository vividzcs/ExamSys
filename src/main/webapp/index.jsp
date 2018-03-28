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

    <link href="${pageContext.request.contextPath }/style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath }/style/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/style/css/style.css?v=2.2.0" rel="stylesheet">
   <style>
   body{
   	background: url(${pageContext.request.contextPath }/style/img/bg .jpg) no-repeat center ;
   	background-position: 500px 200px;
   }
  .login .role {
    margin:35% auto;     	
   }
     .login .role a {
	 display:block;
     	float: left;
     	list-style: none;
     	width: 200px;
     	height: 150px; 
     	border-radius: 30%;
     	text-align: center;
     	font: 40px/150px "微软雅黑";
     	margin-left: 20px;
     	box-shadow: 2px 2px 10px #444444;
     }
          .code {
          	border: 1px solid #990000;
            font-family: Arial;
            font-style: italic;
            border: 0;
            padding: 2px 3px;
            width: 150px;
            height:50px;
            font-size: 18px;
        }
        .unchanged {
            border: 0;
        }
        
        .code_input {
        	width:50%;
        	float:left;
        }
   </style>
</head>

<body>
<div class="  loginscreen  animated fadeInDown">
	<div class="col-lg-3"></div>
 <div class="container  login col-lg-6">
    <div class="list-group role m-t">
		<div class="col-lg-4"><a class="btn btn-info btn-rounded" href="./student/index.html">学生</a></div>
         <div class="col-lg-4"><a class="btn btn-warning btn-rounded" data-toggle="modal" data-target="#teacherModal" href="#" onclick="changeCode()">教师</a></div>
        <div class="col-lg-4"><a class="btn btn-danger btn-rounded" data-toggle="modal" data-target="#adminModal" href="#" onclick="changeCode()">管理员</a></div>
  	 <!--  <ul> 
  	    <li class="col-sm-12">  <a href="./views/indexStudent.html">学生</a></li>
  	    <li class="col-sm-12" data-toggle="modal" data-target="#teacherModal"> <a href="#" >教师</a></li>
  	    <li class="col-sm-12" data-toggle="modal" data-target="#teacherModal">  <a href="#" onclick="createCode()">管理员</a></li>
  	    </ul>-->
    </div>
    <div class="col-lg-3"></div>
      <!-- 模态框 -->
 </div>
</div>
 
  <!-- 模态框 -->
  <div class="modal fade" id="teacherModal" style="height: auto">
    <div class="modal-dialog">
      <div class="modal-content">
   
        <!-- 模态框头部 -->
        <div class="modal-header">
          <h4 class="modal-title">教师登录系统</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        <!-- 模态框主体 -->
        </div>
        <div class="modal-body">
           <form class="form-horizontal" id="teacherLogin">
               <div class="form-group">
                  <label class="col-lg-3 control-label">用户名：</label>
                     <div class="col-lg-8">
                     <input type="username" placeholder="用户名" class="form-control" id="t_number"  required="">
                  </div>
               </div>
               <div class="form-group">
                <label class="col-lg-3 control-label">密码：</label>
                   <div class="col-lg-8">
                       <input type="password" id="t_pass" placeholder="密码" class="form-control"  required="">
                          </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-3 control-label">验证码</label>
                        <div class="col-lg-8">
                        	<input type="text" placeholder="验证码" id="t_code" class="form-control code_input" required="" />&nbsp;&nbsp;&nbsp;<img class="code" src="${pageContext.request.contextPath }/Kaptcha.jpg" alt="验证码位置" onclick="changeCode()" />
                      </div>
                      </div>
                      <div class="form-group">
                     <div class="col-lg-offset-3 col-lg-8">
                      <button class="btn btn-primary" type="button" id="t_login">登 录</button>&nbsp;&nbsp;&nbsp;<span id="t_warn"></span>
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
  <div class="modal fade" id="adminModal" style="height: auto" >
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
                     <input type="username" placeholder="用户名" class="form-control" id="ad_number" required="">
                  </div>
               </div>
               <div class="form-group">
                <label class="col-lg-3 control-label">密码：</label>
                   <div class="col-lg-8">
                       <input type="password" placeholder="密码" class="form-control" id="ad_pass" required="">
                          </div>
                  </div>
                  <div class="form-group">
                      <label class="col-lg-3 control-label">验证码</label>
                        <div class="col-lg-8">
                        	<input type="text" placeholder="验证码" id="ad_code" class="form-control code_input" required="" />&nbsp;&nbsp;&nbsp;<img class="code" src="${pageContext.request.contextPath }/Kaptcha.jpg" alt="验证码位置"  onclick="changeCode()" />
                      </div>
                      </div>
                      <div class="form-group">
                     <div class="col-lg-offset-3 col-lg-8">
                      <button class="btn btn-primary" type="button" id="ad_login">登 录</button>&nbsp;&nbsp;&nbsp;<span id="ad_warn"></span>
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
    <script src="${pageContext.request.contextPath }/style/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/style/js/bootstrap.min.js?v=3.4.0"></script>
    <script>
	
	$("#t_login").click(function() {
		var t_number = $("#t_number").val();
		var t_pass = $("#t_pass").val();
		var code = $("#t_code").val();
		var data = {t_number:t_number,t_pass:t_pass,code:code};
		$.post('${pageContext.request.contextPath}/teacher_login.action',data,function(data){
			//console.log(data)
			if(data.status == 1) {
				//登录成功
				$(window).attr("location","${pageContext.request.contextPath}/teacher/");
			} else {
				//展示错误数据
				$("#t_warn").css("display","inline-block").css("color","red").html(data.msg);
			}
		},"json")
		
	})
	
	$("#ad_login").click(function() {
		var ad_number = $("#ad_number").val();
		var ad_pass = $("#ad_pass").val();
		var code = $("#ad_code").val();
		var data = {ad_number:ad_number,ad_pass:ad_pass,code:code};
		$.post('${pageContext.request.contextPath}/admin_login.action',data,function(data){
			//console.log(data)
			if(data.status == 1) {
				//登录成功
				$(window).attr("location","${pageContext.request.contextPath}/admin/");
			} else {
				//展示错误数据
				$("#ad_warn").css("display","inline-block").css("color","red").html(data.msg);
			}
		},"json")
		
	})
 	
	function changeCode() {
    	$(".code").attr("src","${pageContext.request.contextPath }/Kaptcha.jpg?code=" + Math.random());
    }
    </script>
</body>

</html>

