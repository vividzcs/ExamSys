// 实例一个模块，用来专门管理所有的控制器
angular.module('Controllers', [])
////开始添加每一个模块需要用的控制器
//管理学生时，添加学生的控制器，比如院系和专业，动态的生成
.controller('department',['$scope','$http',function($scope,$http){
	   //首先要添加我们选项内的院系信息
//	   先要获取到院系信息
//      $scope.departments = [
//      {
//      	department:'信息科学与技术学院',
//      	profess:['软件工程','计算机科学与技术','电子信息','通信工程']
//      },
//       {
//      	department:'数学学院',
//      	profess:[ '金融数学','应用数学','数学建模','通信工程' 	]
//      },
//        {
//      	department:'化学材料学院',
//      	profess:['应用化学','材料管理','应用化学','通信工程1']
//      }
//      ];
        $scope.changeClassification=function(selectD){
        	$scope.profess=selectD.profess;
        }
        
        //使用时利用http
     $http({
     	url:'${pageContext.request.contextPath }/admin/student_mes.action',
     	method:'get'
     }).success(function(info){
     	  $scope.departments=info;
     })
    
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
$scope.selectStyle='计算机科学';
  console.log($scope.selectStyle);
  $scope.choices=["A","B","C","D"];
  $scope.datas=[
      {type:'sChoice',content:{qTitle:"我国的现状？",answer:["繁荣","富强","美好","优秀"]}},
      {type:'sChoice',content:{qTitle:"http的状态码正确的是？",answer:["繁荣","富强","美好","优秀"]}},
      {type:'explanation',content:"我国的现状？"},
      {type:'explanation',content:"对于计算机科学的历史有哪些了解？"},
      {type:'explanation',content:"http的传送数据方法有哪些？"},
      ]	
      
	 
}])