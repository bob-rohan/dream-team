// Config
fplApp.config(function($routeProvider){
   
    $routeProvider
    
    .when('/', {
        templateUrl: 'home.html',
        controller: 'fplController'
    })
    
});