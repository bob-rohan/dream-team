// Directives
fplApp.directive("tablePanel", function(){
   return {
       restrict: 'E',
       templateUrl: '../html/tablePanel.html',
       replace: true,
       scope: {
           // @ String
           typeName: "@",
           // = Object
           sortType: "=",
           players: "=",
           gameweekss: "=",
           configuration: "="
       },
       controller: ["$scope", "$rootScope", function($scope, $rootScope) {
	    
    	   $scope.toggleSelected = function(player){
    		   $rootScope.toggleSelected(player);
    	   }
       }]
   } 
    
});

fplApp.directive("priceChangePanel", function(){
	   return {
	       restrict: 'E',
	       templateUrl: '../html/priceChangePanel.html',
	       replace: true,
	       scope: {
	           // = Object
	           sortType: "=",
	           players: "="
	       }
	   } 
	    
	});

fplApp.directive("fitnessPanel", function(){
	   return {
	       restrict: 'E',
	       templateUrl: '../html/fitnessPanel.html',
	       replace: true,
	       scope: {
	           // = Object
	           sortType: "=",
	           players: "="
	       }
	   } 
	    
	});

fplApp.directive("squadPanel", function(){
	   return {
	       restrict: 'E',
	       templateUrl: '../html/squadPanel.html',
	       replace: true,
	       scope: {
	           // = Object
	           sortType: "=",
	           players: "=",
	           gameweekss: "=",
	           configuration: "="
	       },
	       controller: ["$scope", "$rootScope", function($scope, $rootScope) {
	    	   
	    	   $scope.toggleSelected = function(player){
	    		   $rootScope.toggleSelected(player);
	    	   }
	       }]
	   } 
	    
	});