// Directives
fplApp.directive("tablePanel", function(){
   return {
       restrict: 'E',
       templateUrl: 'tablePanel.html',
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