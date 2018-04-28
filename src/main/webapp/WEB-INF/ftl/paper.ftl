<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>学生考试系统</title>
    <meta name="keywords" content="学生考试系统，专为学生打造在校考试功能">
    <meta name="description" content="大学生在线考试系统，学生在网络上在线填写试卷">
    <!--<link href="../../../../style/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">-->
    <link href="../../../style/css/main.css" rel="stylesheet" type="text/css" />
    <link href="../../../style/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="../../../style/css/test.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" charset="utf-8" src="../../../style/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../../../style/plugins/ueditor/ueditor.all.min.js"> </script>
     <script type="text/javascript" charset="utf-8" src="../../../style/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
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
 
	<script>
	 
	  function checkstart(){  	
		if(confirm("是否确认提交试卷？")){
			//选择题
			var clength = document.getElementsByClassName("choice").length;
			for(var i=1;i<clength+1;i++){
				var answer = document.getElementsByName("answer"+i);
				var flag=0;
			
			for(var j=0;j<4;j++){
				if(answer[j].checked){
					flag++;
				}
			}
			if(flag==0){
				if(confirm("选择题有空题目！是否坚持交卷"))
					return true;
				else 
					return false;
			}
			}

			//判断题
			var jlength = document.getElementsByClassName("judge").length;
			for(var i=1;i<jlength+1;i++){
				var janswer = document.getElementsByName("janswer"+i);
				var jflag=0;
			
			for(var j=0;j<2;j++){
				if(janswer[j].checked){
					jflag++;
				}
			}
			if(jflag==0){
				if(confirm("判断题有空题目！是否坚持交卷"))
					return true;
				else 
					return false;
			}
			}
			/* var gender=$('input:radio[name="answer1"]:checked').val();	
			if(!gender){
				if(confirm("卷面为空 是否继续提交"))
					return true;
				else 
					return false;
			}*/
			
			//填空题
			var flength = document.getElementsByClassName("fill").length;
			for(var i=1;i<flength+1;i++){
				var fanswer = document.getElementsByName("fanswer"+i);
				var fflag=0;
				if(fanswer.value!==null)
					fflag++;
				
			if(fflag==0){
				if(confirm("主观大题有空题目！是否坚持交卷"))
					return true;
				else 
					return false;
			}
			}

			
		
		}
		
		else 
			return false;
	
	}
	</script>
	
	</head>
	<body ng-app="exam">
  <!--在这个页面中将返回到的题目信息添加到页面上-->
    <div class="problem" ng-controller="createTestPaper" ng-cloak>
    	 <div class="main">
	<div class="test_content_title">
				<a href="javascript:history.go(-1)" class="content_lit"><p>&nbsp;&nbsp;&nbsp;退出&nbsp;&nbsp;&nbsp;</p></a>
				</div>
	<!--nr start-->
	<div class="test_main">
		<div class="nr_left">
			<div class="test">
				
				<form action="${contextPath}/student/more/student_pushPaper.action" method="post"  >
					<div width="200px" >
                 <span>试卷编号：${paper.pap_id}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <span>考试科目：${subject}</span >
				
                 </div>
					<div class="test_title">
						<p class="test_time">
							<i class="icon iconfont">&#xe6fb;</i><b class="alt-1">02:00</b>
						</p>
						<font><input type="submit" value="交卷"  onclick=" return checkstart()"></font>
						<input type="hidden" value="${paper.pap_id}" name="uuid">
					</div>
						<ul>
						<#if cPaperList??>
						<li>
						<div class="test_content">
							<div class="test_content_title">
								<h2>单选题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.single_choice_num}</i><span>题，</span><span>合计</span><i class="content_fs">${rule.single_choice_score}</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul >
								<#list cPaperList as cquestion>
									<li id="qu_0_${cquestion_index}" class="choice">
										<div class="test_content_nr_tt">
											<i>${cquestion_index+1}</i><span></span><font>${cquestion[0]} </font><b class="icon iconfont">&#xe881;</b>
										</div>

										<div class="test_content_nr_main">
											<ul>
												
													<li class="option">
														
															<input type="radio" class="radioOrCheck" name="choice${cquestion_index}" value="0"
																id="${cquestion_index}_answer_1_option_1"
															/>
														
														
														<label for="${cquestion_index}_answer_1_option_1">
															A.
															<p class="ue" style="display: inline;">${cquestion[1]}</p>
														</label>
													</li>
												
													<li class="option">
														
															<input type="radio" class="radioOrCheck" name="choice${cquestion_index}" value="1"
																id="${cquestion_index}_answer_1_option_2"
															/>
														
														
														<label for="${cquestion_index}_answer_1_option_2">
															B.
															<p class="ue" style="display: inline;">${cquestion[2]}</p>
														</label>
													</li>
												
													<li class="option">
														
															<input type="radio" class="radioOrCheck" name="choice${cquestion_index}" value="2"
																id="${cquestion_index}_answer_1_option_3"
															/>
														
														
														<label for="${cquestion_index}_answer_1_option_3">
															C.
															<p class="ue" style="display: inline;">${cquestion[3]}</p>
														</label>
													</li>
												
													<li class="option">
														
															<input type="radio" class="radioOrCheck" name="choice${cquestion_index}" value="3"
																id="${cquestion_index}_answer_1_option_4"
															/>
														
														
														<label for="${cquestion_index}_answer_1_option_4">
															D.
															<p class="ue" style="display: inline;">${cquestion[4]}</p>
														</label>
													</li>
												
											</ul>
										</div>
									</li>
								</#list>
							</ul>
						</div>
						</li>
					</#if>
					<#if jPaperList??>
						<li>
						<div class="test_content">
							<div class="test_content_title">
								<h2>判断题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.judge_num}</i><span>题，</span><span>合计</span><i class="content_fs">${rule.judge_score}</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul >
								<#list jPaperList as jquestion>
								<li id="qu_1_${jquestion_index}" class="judge">
										<div class="test_content_nr_tt">
											<i>${jquestion_index+1}</i><span></span><font>${jquestion[0]}</font><b class="icon iconfont">&#xe881;</b>
										</div>

										<div class="test_content_nr_main">
											<ul>
												
													<li class="option">
														
														
															<input type="radio" class="radioOrCheck" name="judge${jquestion_index}" value="1"
																id="${jquestion_index}_answerj_1_option_1"
															/>
														
														<label for="${jquestion_index}_answerj_1_option_1">
															<p class="ue" style="display: inline;">是</p>
														</label>
													</li>
												
													<li class="option">
														
														
															<input type="radio" class="radioOrCheck" name="judge${jquestion_index}"  value="0"
																id="${jquestion_index}_answerj_1_option_2"
															/>
														
														<label for="${jquestion_index}_answerj_1_option_2">
															<p class="ue" style="display: inline;">否</p>
														</label>
													</li>
											</ul>
										</div>
									</li>
								</#list>
								</ul>
							 </div>
						 </li>
						</#if>				
						<#if bPaperList??>
						<li>
						<div class="test_content">
							<div class="test_content_title">
								<h2>填空题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.blank_num}</i><span>题，</span><span>合计</span><i class="content_fs">${rule.blank_score}</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul class="subjective">
								<#list bPaperList as bquestion>
									<li id="qu_2_${bquestion_index}" class="fill">
										<div class="test_content_nr_tt">
											<i>${bquestion_index+1}</i><span></span><font>${bquestion[0]}</font><b class="icon iconfont">&#xe881;</b>
										</div>
										<div class="test_content_nr_main">
									        <div class="row ">
                    	                         <textarea name="blank" rows="" cols=""></textarea>
                                            </div>
										      
										</div>
									</li>
									</#list>
								</ul>
							</div>
						</li>
					</#if>					
					<#if tPaperList??>
					<li>
						<div class="test_content">
							<div class="test_content_title">
								<h2>名词解释</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.translate_num}</i><span>题，</span><span>合计</span><i class="content_fs">${rule.translate_score}</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul >
								<#list tPaperList as tquestion>
									<li id="qu_3_${tquestion_index}" class="fill">
										<div class="test_content_nr_tt">
											<i>${tquestion_index+1}</i><span></span><font>${tquestion[0]}</font><b class="icon iconfont">&#xe881;</b>
										</div>
										<div class="test_content_nr_main">
									        <div class="row ">
                    	                         <textarea name="translate" rows="" cols=""></textarea>
                                            </div>
										      
										</div>
									</li>
									</#list>
								</ul>
							</div>
						</li>
						</#if>
								
						<#if simPaperList??>			
						<li>
						<div class="test_content">
							<div class="test_content_title">
								<h2>简答</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.simple_question_num}</i><span>题，</span><span>合计</span><i class="content_fs">${rule.simple_question_score}</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul class="subjective">
								<#list simPaperList as simquestion>
									<li id="qu_4_${simquestion_index}" class="fill">
										<div class="test_content_nr_tt">
											<i>${simquestion_index+1}</i><span>(1分)</span><font>${simquestion[0]}</font><b class="icon iconfont">&#xe881;</b>
										</div>
										<div class="test_content_nr_main">
									        <div class="row ">
                    	                         <script name="simple" id="simple${simquestion_index}"></script>
                                            </div>
										      
										</div>
									</li>
									</#list>
								</ul>
							</div>
							</li>
						</#if>
						<#if comPaperList??>
						<li>
						<div class="test_content">
							<div class="test_content_title">
								<h2>计算题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.compute_num}</i><span>题，</span><span>合计</span><i class="content_fs">${rule.compute_score}</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul class="subjective">
								<#list comPaperList as comquestion>
									<li id="qu_5_${comquestion_index}" class="fill">
										<div class="test_content_nr_tt">
											<i>${comquestion_index+1}</i><span></span><font>${comquestion[0]}</font><b class="icon iconfont">&#xe881;</b>
										</div>
										<div class="test_content_nr_main">
									        <div class="row ">
                    	                         <script name="compute" id="compute${comquestion_index}"></script>
                                            </div>
										      
										</div>
									</li>
									</#list>
								</ul>
							</div>
							</li>
							</#if>			
							<#if mPaperList??>
							<li>
						<div class="test_content">
							<div class="test_content_title">
								<h2>综合</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.mix_num}</i><span>题，</span><span>合计</span><i class="content_fs">${rule.mix_score}</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul class="subjective">
								<#list mPaperList as mquestion>
									<li id="qu_6_${mquestion_index}" class="fill">
										<div class="test_content_nr_tt">
											<i>${mquestion_index+1}</i><span></span><font>${mquestion[0]}</font><b class="icon iconfont">&#xe881;</b>
										</div>
										<div class="test_content_nr_main">
									        <div class="row ">
                    	                         <script name="mix" id="mix${mquestion_index}"></script>
                                            </div>
										      
										</div>
									</li>
									</#list>
								</ul>
							</div>
						</li>
						</#if>		
			 </ul>
			 </form>
	 </div>
					
				
			</div>

		</div>
		<div class="nr_right">
			<div class="nr_rt_main">
				<div class="rt_nr1">
					<div class="rt_nr1_title">
						<h1>
							<i class="icon iconfont">&#xe692;</i>答题卡
						</h1>
						<p class="test_time">
							<i class="icon iconfont">&#xe6fb;</i><b class="alt-1">02:00</b>
						</p>
					</div>
					<#if cPaperList??>
						<div class="rt_content">
							<div class="rt_content_tt">
								<h2>单选题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.single_choice_num}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<#list cPaperList as cquestion>
										<li><a href="#qu_0_${cquestion_index}">${cquestion_index+1}</a></li>
									</#list>
									
								</ul>
							</div>
						</div>
						</#if>
						<#if jPaperList??>
						<div class="rt_content">
							<div class="rt_content_tt">
								<h2>判断题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.judge_num}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
								<#list jPaperList as jquestion>
										<li><a href="#qu_1_${jquestion_index}">${jquestion_index+1}</a></li>
								</#list>
									
								</ul>
							</div>
						</div>
						</#if>
						<#if bPaperList??>
						<div class="rt_content">
							<div class="rt_content_tt">
								<h2>填空题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.blank_num}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<#list bPaperList as bquestion>
										<li><a href="#qu_2_${bquestion_index}">${bquestion_index+1}</a></li>
									</#list>
								</ul>
							</div>
						</div>
						</#if>
						<#if tPaperList??>
						<div class="rt_content">
							<div class="rt_content_tt">
								<h2>名词解释</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.translate_num}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<#list tPaperList as tquestion>
										<li><a href="#qu_3_${tquestion_index}">${tquestion_index+1}</a></li>
									</#list>
								</ul>
							</div>
						</div>
						</#if>
						
						<#if simPaperList??>
							<div class="rt_content">
							<div class="rt_content_tt">
								<h2>简答题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.simple_question_num}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<#list simPaperList as simquestion>
										<li><a href="#qu_4_${simquestion_index}">${simquestion_index+1}</a></li>
									</#list>
								</ul>
							</div>
						</div>
						</#if>
						
						<#if comPaperList??>
							<div class="rt_content">
							<div class="rt_content_tt">
								<h2>计算题</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.compute_num}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
								<#list comPaperList as comquestion>
										<li><a href="#qu_5_${comquestion_index}">${comquestion_index+1}</a></li>
								</#list>
								</ul>
							</div>
						</div>
						</#if>
						
						<#if mPaperList??>
							<div class="rt_content">
							<div class="rt_content_tt">
								<h2>综合</h2>
								<p>
									<span>共</span><i class="content_lit">${rule.mix_num}</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<#list mPaperList as mquestion>
										<li><a href="#qu_6_${mquestion_index}">${mquestion_index+1}</a></li>
									</#list>
								</ul>
							</div>
						</div>
						</#if>
					
				</div>

			</div>
		</div>
	</div>
   </div>
 
<script src="../../../style/js/jquery-1.11.3.min.js"></script>
<script src="../../../style/js/jquery.easy-pie-chart.js"></script>
<!--时间js-->
<script src="../../../style/js/jquery.countdown.js"></script>
<script type="text/javascript">
function getData() {
        //jq发ajax
        $.ajax({
                url:'02.waterfall.php',//请求路径
                dataType:'json',
                success:function(backData){
                        // 数据 有了 定义模板 生成html标签
                        // id  对象  {items:[]}
//                 标识注意
//由于在后台取出的数据可能是问题的集合,backData包含了所有的问题,因此对于每种类型题,要进行数据分离
                     
                   //1;单选题  
                     var strSimple = template('templateSimple',backData.choice);
             //单选题的class:test_content_nr
                 $(".choice").append(strSimple);
            
                   //2.判断题：
                   var strJudge =template('templateSimple',backData.judge);
                   $(".judge").append(strJudge);
                   //3：主观题
                   var strSubjective =template('templatESubjective',backData.subjective);
                   $(".subjective").append(strSubjective);
                   
                }
        })
}
</script>
<script>
	/**
	* 初始化考试剩余时间
	*/
	$.ajaxSetup({  
        async : false //取消异步  
    }); 
	$(function(){
		var p_id = ${rule.p_id};
		var data = {p_id:p_id};
		$.post('${contextPath}/student/getEndTime.action',data,function(data){
			var time = (data.endTime - new Date().getTime())/1000
			var hour = Math.floor(time/3600);
    		var minu = Math.floor((time-(hour*3600))/60);
            var second = Math.floor(time-hour*3600-minu*60)
			$(".alt-1").html(hour+':'+minu+':'+second);  //01:40
		},"json")
	})
	
	window.jQuery(function($) {
		"use strict";
		
		$('time').countDown({
			with_separators : false
		});
		$('.alt-1').countDown({
			css_class : 'countdown-alt-1'
		});
		$('.alt-2').countDown({
			css_class : 'countdown-alt-2'
		});
		
	});
	
	
	$(function() {
		$('li.option label').click(function() {
		debugger;
			var examId = $(this).closest('.test_content_nr_main').closest('li').attr('id'); // 得到题目ID
			var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
			// 设置已答题
			if(!cardLi.hasClass('hasBeenAnswer')){
				cardLi.addClass('hasBeenAnswer');
			}
			
		});



	});
	//ueditor
	<#list simPaperList as simquestion>
		var simple${simquestion_index} = UE.getEditor('simple${simquestion_index}');
	</#list>
	<#list comPaperList as comquestion>
			var compute${comquestion_index} = UE.getEditor('compute${comquestion_index}');
	</#list>
	<#list mPaperList as mquestion>
		var mix${mquestion_index} = UE.getEditor('mix${mquestion_index}');
	</#list>
	
	
</script>



   </body>	
   
</html>