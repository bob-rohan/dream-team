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