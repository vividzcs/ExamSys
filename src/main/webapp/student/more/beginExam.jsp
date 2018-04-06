<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <link href="../../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../../style/css/animate.css" rel="stylesheet">
    <link href="../../style/css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body ng-app="exam" ng-controller="createTestPaper">
		<div style="height: 100%;" class=" row border-bottom white-bg dashboard-header" >

		<div> 
			<div width="200px" >
                 <h5>试卷编号：</h5>
				 <h5>答题人：</h5>
            </div>
		</div>
        </br></br>
			 <div class="row">
                    <div class="col-lg-6">
                        <div class="ibox ">
                            <div class="ibox-title">
                                <h5>试卷题目</h5>
                            </div>
                            <div class="ibox-content">
                                     <iframe    name="iframe0" width="100%" height="550px" src="ScreatePaper.html" frameborder="0" data-id="hello.html" seamless></iframe>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="ibox ">
                            <div class="ibox-title">
                                <h5>答题纸</h5>
                            </div>
                            <div class="ibox-content">
                               <iframe    name="iframe0" width="100%" height="550px" src="ScreatePaper.html" frameborder="0" data-id="hello.html" seamless></iframe>
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
    <script src="../../style/js/jquery-2.1.1.min.js"></script>
    <script src="../../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../../style/js/hplus.js?v=2.2.0"></script>
    <script src="../../style/js/plugins/pace/pace.min.js"></script>
       <!--引入必须的anjular-->
  <script src="../../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src="../../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
   <script src="../../style/js/anjularJS/anjularjs/app.js"></script>
   <script src="../../style/js/anjularJS/anjularjs/control.js"></script>


</body>

</html>