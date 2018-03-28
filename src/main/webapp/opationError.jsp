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
			background: rgba(200,200,200,0.5);
			border-radius: 10px;
			position: relative;
		}
		.information h2,.information h3{
			text-align: center;
		}
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
			<h2>操作失败</h2>
			<h3>${info }</h3>
		  <a href="javascript:history.go(-1)"><button class="btn btn-success" >返回</button></a> 
		</div>
	</body>
</html>
