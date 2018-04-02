// 实例一个模块，用来专门管理所有的控制器
angular.module('Controllers', [])
////开始添加每一个模块需要用的控制器
//管理学生时，添加学生的控制器，比如院系和专业，动态的生成
.controller('department',['$scope','$http',function($scope,$http){
	   //首先要添加我们选项内的院系信息
//	   先要获取到院系信息
      $scope.departments = [
      {
      	department:'信息科学与技术学院',
      	profess:['软件工程','计算机科学与技术','电子信息','通信工程']
      },
       {
      	department:'数学学院',
      	profess:[ '金融数学','应用数学','数学建模','通信工程' 	]
      },
        {
      	department:'化学材料学院',
      	profess:['应用化学','材料管理','应用化学','通信工程1']
      }
      ];
        $scope.changeClassification=function(selectD){
        	$scope.profess=selectD.profess;
        }
        
        //使用时利用http
//     $http({
//     	url:'返回院系和专业的后台文件',
//     	method:'get'
//     }).success(function(info){
//     	  $scope.departments=info;
//     })
//    
}])
//专业和科目的控制器
.controller("profess",["$scope","$http",function($scope,$http){
	     $scope.professes = [
      {
      	profess:'软件工程',
      	course:['项目管理1','项目管理2','项目管理3','项目管理4']
      },
       {
      	profess:'应用数学',
      	course:[ '金融数学','应用数学','数学建模','到的' 	]
      },
        {
      	profess:'电子信息',
      	course:['通信','信息论','项目管理','html']
      }
      ];
      $scope.selectD=$scope.professes[0].profess;
	  $scope.changeClassification=function(selectD){
        	$scope.course=selectD.course;
        }
}])
////管理教师的控制器
//.controller('teacher',['$scope','$http',function($scope,$http){
//	   $scope.tdepartment=["信息科学与技术学院","文学院","公共管理学院","经济管理学院","数学学院"];
//	   
//}])
////试卷类
//.controller('paper',['$scope','$http',function($scope,$http){
//	$scope.paperStyle=['文学类','公共课','理学类','通识课'];
//	$scope.courseStyle=['计算机类','文学类','数学类','经济类'];
//	//试卷信息
//	$scope.courseMessage=[
//	{name:"中国近代史",status:"正在使用"},
//	{name:"软件工程", status:"正在使用"},
//	{name:"计算机科学",status:"正在使用"},
//	{name:"电子信息",status:"正在使用"}
//	];
//	//点击
//}])
////学生试题生成模板
// 
.controller('createTestPaper',['$scope','$http',function($scope,$http){
  
  $scope.choices=["A","B","C","D"];
  $scope.practice=false;
    $scope.datas=[
      {type:'sChoice',content:{qTitle:"我国的现状？",answer:["繁荣","富强","美好","优秀"]}},
      {type:'sChoice',content:{qTitle:"http的状态码正确的是？",answer:["繁荣","富强","美好","优秀"]}},
      {type:'explanation',content:"我国的现状？"},
      {type:'explanation',content:"对于计算机科学的历史有哪些了解？"},
      {type:'explanation',content:"http的传送数据方法有哪些？"},
      ]	;
 $scope.toggle=function(){
   
   $scope.practice=!$scope.practice;
  }
  
      
      
      
	 
}])