<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head   >

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
    <style>
    	.practice,.answer{
    		height: 670px;
    		border-right: 3px dashed #eee;
    		overflow: scroll;
    	}
    	iframe{
         height:650px;
    	}
    	.practice .practice-title{
    	}
    </style>
  </head>

<body  ng-app="exam"  ng-controller="createTestPaper" >
	<div style="height: 100%;" class=" row border-bottom white-bg dashboard-header" >
		<div class="col-lg-7" > 
		<form action="${pageContext.request.contextPath }/teacher/teacher_beginReview.action" method="post">
			<input type="hidden" name="m_id" value="${m_id }">
			<input type="hidden" name="sub_id" value="${sub_id }">
		    <div class="form-group">
			      <div class="col-sm-9 col-sm-offset-10">
				   <button class="btn btn-primary" ng-click="toggle()" type="">阅下一份卷</button>
			      </div>
           </div>
           </form>
          </div>
      </br></br><br />
			 <div class="row"  ng-cloak ng-show="practice" >
                    <div class="col-lg-6 practice" >
                        <!--<div class="ibox ">
                            <div class="ibox-title practice-title">
                                <h5>练习题目</h5>
                            </div>
                            <div class="ibox-content">
                                   <div class="row J_mainContent" id="content-main">-->
			                     <!--<iframe    name="iframe0" width="100%" height="550px" src="ScreatePaper.html" frameborder="0" data-id="hello.html" seamless></iframe>-->
			                      <!--</div>
                            </div>-->
                        </div>
                    </div>
                    <!--<div class="col-lg-6 answer">
                        <div class="ibox ">
                            <div class="ibox-title">
                                <h5>答题纸</h5>
                            </div>
                            <div class="ibox-content">
                                 <div class="row J_mainContent" id="content-main">
			                     <iframe  name="iframe0" width="100%" height="550px" src="answerPaper.html" frameborder="0" data-id="hello.html" ></iframe>
			                      </div>

                            </div>

                        </div>
                    </div>-->
                </div>
        </div>
    <!-- Mainly scripts -->
    <script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
      <!--引入必须的anjular-->
  <script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
  <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
   <script src="../style/js/anjularJS/anjularjs/app.js"></script>
   <script src="../style/js/anjularJS/anjularjs/control.js"></script>

</body>
<script>
/**
 * 专业和科目的联动
 */
var allData;
 $(function(){
	  $.post('${pageContext.request.contextPath }/student/mesFS.action',{},function(data){
		  allData = data;
			var strProf = '<option value="">-- 请选择 --</option>';
			for(var i in data) {
				strProf += '<option value="' + i + '">'+ data[i].profess +'</option>'
			}
			
			$("#profess").html(strProf);
			
		},"json")
 })
 
 $("#profess").change(function(){
	  var id = $(this).val();
	  if(id != '') {
		  var sub = $("#subject");  
		  var strSub = '<option value="">-- 请选择 --</option>';
		  var subjects = allData[id];
		  for(var i in subjects.subject) {
			strSub += '<option value="' + i + '">'+ subjects.subject[i] +'</option>'
			}
		sub.html(strSub);
	  }
	  
 });
</script>
</html>