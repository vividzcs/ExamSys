<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>学生考试系统</title>
    <meta name="keywords" content="学生考试系统，专为学生打造在校考试功能">
    <meta name="description" content="大学生在线考试系统，学生在网络上在线填写试卷">
    <link href="../../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <style>
    	.problem{
    		cursor: pointer;
    		padding: 5px;
    		font-size: 15px;
    	}
    	.problem ul li{
    		list-style: none;
    	}
    	.problem ul li .answer{
    		padding-left: 10px;
    	}
    </style>
	</head>
	<body ng-app="exam">
  <!--在这个页面中将返回到的题目信息添加到页面上-->
    <div class="problem" ng-controller="createTestPaper" ng-cloak>
    	  <div class="qTitle">所练习科目：${subject}</div>
    	<#if cPaperList??>
    		<!--如果有选择题-->
    		<#list cPaperList as cquestion>
    		<ul class="qList">
				 <!--题目列表-->
				 <!--数据应该有 题的类型-->
				<li ng-if="data.type == 'sChoice'">
					<!--这行写题目以及题目的编号-->
					<!--先要判断提醒-->
					<div class="eachProblem">
						<b>${cquestion_index}.&nbsp;${cquestion[0]}</b>
							<div  class="answer" ng-repeat="(key,answer) in data.content.answer" >
								 <span><b style="margin-left: 10px;">A.</b>${cquestion[1]} </span>
								 <span><b style="margin-left: 10px;">B.</b>${cquestion[2]} </span>
								 <span><b style="margin-left: 10px;">C.</b>${cquestion[3]} </span>
								 <span><b style="margin-left: 10px;">D.</b>${cquestion[4]} </span>
							</div>
					</div>
				</li>
				<li ng-if="data.type == 'explanation'">
					 <div class="eachProblem">
					 	<!--<b>{{key+1}}.&nbsp;{{data.content}}</b>-->
					</div>
				</li>
			</ul>
			</#list>
    	</#if>
    	
   </div>
  <!--引入必须的anjular-->
 <!--  <script src="../../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src="../../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
   <script src="../../style/js/anjularJS/anjularjs/app.js"></script>
   <script src="../../style/js/anjularJS/anjularjs/control.js"></script> -->
   </body>	
   
</html>