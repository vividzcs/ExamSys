<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="exam">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>学生考试系统</title>
    <meta name="keywords" content="学生考试系统，专为学生打造在校考试功能">
    <meta name="description" content="大学生在线考试系统，学生在网络上在线填写试卷">

    <link href="../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="../style/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <!-- Morris -->
    <link href="../style/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="../style/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="../style/css/animate.css" rel="stylesheet">
    <link href="../style/css/style.css?v=2.2.0" rel="stylesheet">
      <link rel="stylesheet" href="../style/css/self.css" />
      <style>
      select{
      	width: 50%;
      }
      table{
      	 	margin-top: 5px;
      }
      table th{
      	text-align: center;
     
      }
      table tbody td{
      	padding: 5px;
      }
      table tbody td input[type='text']{
       width: 120px;
      }
      </style>
</head>

<body ng-controller="profess">
<!--侧栏部分结束-->
<!--右侧的部分-->
            <!--右侧的顶部结束-->
            <div class="row  border-bottom white-bg dashboard-header" style="height: 100%;">
				       <div class="col-lg-12" class="paperRule">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h3> 试卷生成规则</h3>
                               <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content">
                                <form class="form-horizontal m-t" id="paperRuleForm" style="width:50%" action="${pageContext.request.contextPath }/admin/paperrule_add.action" method="post">
                                <div class="form-group">
                                        <label class="col-sm-3 control-label">规则名：</label>
                                        <div class="col-sm-8">
                                            <input id="username" name="p_name" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                        </div>
                                    </div>
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
                                        <label class="col-sm-3 control-label">试题难度</label>
                                        <div class="col-sm-8">
                                        	<span class="sex">易</span>&nbsp;&nbsp;<input   type="radio" name="degree" value="0" checked="checked"> &nbsp;&nbsp;
                                        	<span class="sex">中</span>&nbsp;&nbsp;<input   type="radio" name="degree" value="1">  &nbsp;&nbsp;
                                          <span class="sex">难</span>&nbsp;&nbsp;<input   type="radio" name="degree" value="2">
                                         </div>
                                        </div>
                                 <div class="form-group">
                                        <label class="col-sm-3 control-label">总分</label>
                                        <div class="col-sm-8">
                                            <input id="totalScore" name="full_score" placeholder="填写数字" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字" value="100">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                <label class="col-sm-3 control-label">试卷题目信息</label>

                                    	<table>
                                    		<thead>
                                    		<tr>
                                    			<th>题目类型</th>
                                    			<th>个数</th>
                                    			<th>题型总分数</th>
                                    		</tr>
                                    		</thead>
                                    		<tbody>
                                    		<tr>
                                    			<td>单选</td>
                                    			<td> <input id="simpleSelect" name="single_choice_num" placeholder="填写数字" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                    			</td>
                                    		    <td>  <input id="simpleSelectScore" name="single_choice_score" placeholder="填写数字" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                    		    </td>
                                    		</tr>
                                    		 <tr>
                                    			<td>判断</td>
                                    			<td> <input id="mulitfySelectScore" name="judge_num" placeholder="填写数字" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                                 </td>
                                    			 <td>   <input id="mulitfySelectScore" name="judge_score" placeholder="填写数字" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                            </td>
                                    		 </tr>
                                    		 <tr>
                                    			<td>填空</td>
                                    			<td> <input id="mulitfySelectScore" name="blank_num" placeholder="填写数字" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                                 </td>
                                    			 <td>   <input id="mulitfySelectScore" name="blank_score" placeholder="填写数字" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber">
                                            </td>
                                    		 </tr>
                                    		 <tr>
                                    			<td>名词解释</td>
                                    			<td>  <input id="example0" name="translate_num" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                               </td>
                                    		   <td> <input id="example0s" name="translate_score" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                                </td>
                                    		 </tr>
                                    		 	 <tr>
                                    			<td>简答题</td>
                                    			<td>  <input id="example1" name="simple_question_num" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                               </td>
                                    		   <td> <input id="example1s" name="simple_question_score" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                                </td>
                                    		 </tr>
                                    		 	 <tr>
                                    			<td>计算</td>
                                    			<td>  <input id="example2" name="compute_num" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                               </td>
                                    		   <td> <input id="example2s" name="compute_score" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                                </td>
                                    		 </tr>
                                    		 	 <tr>
                                    			<td>综和</td>
                                    			<td>  <input id="example3" name="mix_num" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                               </td>
                                    		   <td> <input id="example3s" name="mix_score" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error snumber" placeholder="填写数字">
                                               </td>
                                    		 </tr>
                                    		</tbody>
                                    	</table>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-3">
                                            <button class="btn btn-primary addStudet" type="submit" onclick="isnumber()"> 确认创建</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                      </div>
<script src="../style/js/jquery-2.1.1.min.js"></script>
    <script src="../style/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="../style/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../style/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../style/js/hplus.js?v=2.2.0"></script>
    <script src="../style/js/plugins/pace/pace.min.js"></script>
    
       <!-- DROPZONE -->
    <script src="../style/js/plugins/dropzone/dropzone.js"></script>
   
    <!--angularjs-->
        <!--引入angular插件-->
    <!--<script src="../style/js/anjularJS/bower_components/angular/angular.min.js"></script>
    <script src="../style/js/anjularJS/bower_components/angular-route/angular-route.js"></script>
    <script src="../style/js/anjularJS/anjularjs/app.js"></script>
    <script src="../style/js/anjularJS/anjularjs/control.js"></script>-->
    <!-- jQuery Validation plugin javascript-->
    <script src="../style/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../style/js/plugins/validate/messages_zh.min.js"></script>

    <!-- iCheck -->
    <script src="../style/js/plugins/iCheck/icheck.min.js"></script>
    <script>
      $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"
       

        });

         //以下为官方示例
        $().ready(function () {
            // validate the comment form when it is submitted
            $("#commentForm").validate();

//          // validate signup form on keyup and submit
//          $("#paperRuleForm").validate({
//              rules: {
//                  simpleSelect:  "required",
//                  mulitfySelect: "required",
//                  example:'required',
//                  simpleSelectScore:  'required',
//                   
//                  mulitfySelectScore:'required',
//                  totalScore:'required'
//              },
//              messages: {
//                   simpleSelect:'填写数目',
//                    mulitfySelect:'填写数目',
//                     example:'填写数目',
//                     simpleSelectScore:'填写单选分数',
//                     mulitfySelectScore:'填写判断分数',
//                     totalScore:'填写总分数'
//              },
//          });
           
             //
        });
         
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