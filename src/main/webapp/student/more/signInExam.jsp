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
	<script>
			function form_check(obj){
				//验证密码一致
			
				var passworda =obj.passwordFirst.value;
				var passwordb =obj.passwordConfirm.value;
				
				if(passworda!=passwordb)
				{
					alert("密码两次不一致");
				}
				
				
				//验证学号
				var id= /^[0-9]*$/;//纯数字的正则表达式
				var sid=obj.studentID.value;
				if(id.test(sid)){					
					alert("学号输入不正确");		
			}	
			}

</script>

</head>

<body>
 <div class="row  border-bottom white-bg dashboard-header" style="height: 100%; ">
   <div class="middle-box text-center loginscreen  animated fadeInDown">
            <h3>欢迎注册考试</h3>
            <form class="m-t" role="form" action="" onsubmit="return form_check(this)">
                <div class="form-group">
                    <input type="text" class="form-control" name="s_number" placeholder="请输入学号" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="s_pass" placeholder="请输入密码" required="">
                </div>
				<div class="form-group">
                    <select  id="subjects" name="sub" class="form-control" > <!--根据数据库中的考试科目内容动态生成-->
  										  <option value="">-- 请选择 --</option>
											</select>
                </div>
                <div class="form-group text-left">
                    <div class="checkbox i-checks">
                        <label class="no-padding">
                            <input type="checkbox"><i></i> 我同意注册协议</label>
                    </div>
                </div>
                <button type="button" onclick="signin()" class="btn btn-primary block full-width m-b">注 册</button>&nbsp;&nbsp;&nbsp;<span id="warn"></span>

            </form>
        </div>
    </div>
        
</div>
  <!-- Mainly scripts -->
    <script src="../../style/js/jquery-2.1.1.min.js"></script>
    <script src="../../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../../style/js/hplus.js?v=2.2.0"></script>
    <script src="../../style/js/plugins/pace/pace.min.js"></script>
		
		
		<script type="text/javascript">
			$(function(){
				getSubjects();				
			})
			
			function getSubjects() {
				var str = '<option value="">-- 请选择 --</option>';	
				var subjects = $("#subjects");
				$.post('${pageContext.request.contextPath}/student/more/student_getSubjects.action',{},function(data){
					//console.log(data)
					for(var i in data){
						str += '<option value="'+ i +'">'+ data[i] +'</option>';	
					}
					subjects.html(str);
					
				},"json")
			}
			
			function signin(){
				var s_number = $("input[name='s_number']").val();
				var s_pass = $("input[name='s_pass']").val();
				var sub = $("#subjects").val();
				console.log(sub)
				
				var d = {s_number:s_number,s_pass:s_pass,sub:sub}
				$.post('${pageContext.request.contextPath}/student/more/student_signin.action',d,function(data){
					if(data.status == 1) {
						//注册成功
						$("#warn").css('color','green').html(data.msg);
						getSubjects();
					} else {
						//展示错误数据
						$("#warn").css("color","red").html(data.msg);
					}	
				
				},'json')
			
			}
			
		</script>
</body>

</html>