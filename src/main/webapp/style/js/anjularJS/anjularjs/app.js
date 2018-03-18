var exam = angular.module('exam',['Controllers']);
exam.config(['$locationProvider', function($locationProvider) {  
         // $locationProvider.html5Mode(true);  
         $locationProvider.html5Mode({
          enabled: true,
          requireBase: false
        });
       }]);
 //对于学生的试卷的形式进行模板进入
 exam.config(['$routeProvider', function ($routeProvider) {
// 	学生的练习数据
	$routeProvider.when('/beginTest', {
		templateUrl: './ScreatePpaer.html',
		controller: 'createTestPaper'
	})

}]);