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
           players: "="
       }
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