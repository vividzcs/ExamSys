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
            <div class="ibox-content  rows" style="height: 100%;">
            	<div class="col-lg-12">
                               <form class="form-horizontal m-t" id="signupForm" action="${pageContext.request.contextPath }/admin/paper_change.action" method="post">
                                <div class="form-group"> 
                                        <label class="col-sm-3 control-label">专业</label>
                                        <div class="col-sm-8">
                                            <select id="profess" name="major.m_id" class="form-control m-b"   ng-model="selected" ng-options="m.profess for m in professes" ng-change="changeClassification(selected)">
                                                 <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">科目</label>
                                        <div class="col-sm-8">
                                             <select id="subject" name="subject.sub_id" class="form-control m-b"   ng-model="selected2" ng-options="m for m in course" >
                                             	  <option value="">-- 请选择 --</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">学号：</label>
                                        <div class="col-sm-8">
                                            <input id="studentID" name="s_number" class="form-control" type="text">
                                        </div>
                                    </div>
                                      <div class="form-group">
                                        <label class="col-sm-3 control-label">姓名：</label>
                                        <div class="col-sm-8">
                                            <input id="studentName" name="s_name" class="form-control" type="text">
                                        </div>
                                    </div>
									<div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary" type="submit">快速更换</button>
                                        </div>
                                    </div>
								</form>
			</div>
</div>
    <!-- Mainly scripts -->
    <script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
	
	<script>
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

</body>

</html>