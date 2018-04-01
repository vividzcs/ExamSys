<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <link href="../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body>
    	 <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
   <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
           <h3>请进行考前登录</h3>

            <form class="m-t" role="form" action="${pageContext.request.contextPath }/student/more/student_login.action" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="学号" required="" name="s_name">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required="" name="s_pass">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

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
    <script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>


</body>

</html>