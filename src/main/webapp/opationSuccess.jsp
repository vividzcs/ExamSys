<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		 <link href=" ${pageContext.request.contextPath }/style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
	
		<style>
		.information{
			margin: 10% auto;
			width: 300px;
			height: 200px;
			background: rgba(123,234,211,0.5);
			box-shadow: rgba(123,221,211,0.6) 0px 1px 10px; 
			border-radius: 10px;
			position: relative;
			padding:10px;
		}
		.information h3,.information h4{
			text-align: center;
			margin-top:10px;
		}
		.information h4{
		line-height:30px;}
		button{
			background: #00B7EE;
			border:0 ;
			width: 200px;
			height: 30px;
			line-height: 30px;
			position: absolute;
			left: 15%;
			bottom: 10px;
			border-color:#00B7EE ;
			border-radius: 5px;
		     
		}
	  
		</style>
		
   
	</head>
	<body>
		<div class="information">
			<h3>操作成功</h3>
			<h4>${info }</h4>
		  <a href="${pageContext.request.contextPath }/hello.jsp"><button class="btn btn-success" >返回首页</button></a> 
		</div>
	</body>
</html>
