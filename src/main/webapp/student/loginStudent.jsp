<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <link href="${pageContext.request.contextPath }/style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="${pageContext.request.contextPath }/style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="${pageContext.request.contextPath }/style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath }/style/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/style/css/style.css?v=2.2.0" rel="stylesheet">

</head>
<style>
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
<body>
    	 <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
   <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
           <h3>学生登录</h3>

            <form class="m-t" role="form">
                <div class="form-group">
                    <input type="text" name="s_number" class="form-control" placeholder="学号" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="s_pass" class="form-control" placeholder="密码" required="">
                </div>
                <div class="form-group">
                      <label class="col-lg-3 control-label">验证码</label>
                        <div class="col-lg-8">
                        	<input type="text" placeholder="验证码" id="code" class="form-control code_input" required="" />&nbsp;&nbsp;&nbsp;<img class="code" src="${pageContext.request.contextPath }/Kaptcha.jpg" alt="验证码位置"  onclick="changeCode()" />
                      </div>
                      </div>
                
                <button id="s_login" type="button" class="btn btn-primary block full-width m-b">登 录</button>&nbsp;&nbsp;&nbsp;<span id="warn"></span>

            </form>
        </div>
    </div>
    </div>
            
<!--<div class="row  border-bottom white-bg dashboard-header">         
        
			<div class="form-group">
				<label class="col-sm-3 control-label">文件域：</label>
				<div class="col-sm-9">
					<input type="file" name="" class="form-control">
				</div>
			</div>
		
</div>-->
    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath }/style/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="${pageContext.request.contextPath }/style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath }/style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${pageContext.request.contextPath }/style/js/hplus.js?v=2.2.0"></script>
    <script src="${pageContext.request.contextPath }/style/js/plugins/pace/pace.min.js"></script>

	<script type="text/javascript">
	$("#s_login").click(function() {
		var s_number = $("input[name='s_number']").val();
		var s_pass = $("input[name='s_pass']").val();
		var code = $("#code").val();
		var data = {s_number:s_number,s_pass:s_pass,code:code};
		$.post('${pageContext.request.contextPath}/student/login.action',data,function(data){
			//console.log(data)
			if(data.status == 1) {
				//登录成功
				//$(window).attr("location","${pageContext.request.contextPath}/student/");
				 window.parent.location.reload();
				//window.location.href = window.location.href;
			} else {
				//展示错误数据
				$("#warn").css("display","inline-block").css("color","red").html(data.msg);
			}
		},"json")
		
	})
	
	function changeCode() {
    	$(".code").attr("src","${pageContext.request.contextPath }/Kaptcha.jpg?code=" + Math.random());
    }
	</script>
</body>

</html>