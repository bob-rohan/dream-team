// Config
fplApp.config(function($routeProvider){
   
    $routeProvider
    
    .when('/', {
        templateUrl: '../html/home.html',
        controller: 'fplController'
    })
    
});