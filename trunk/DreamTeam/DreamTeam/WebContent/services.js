// Services
fplApp.service('fplService', ['$resource', '$http', '$log', function($resource, $http, $log){

	
	
	this.getPlayers = function(){
        
        var GetPlayersResource = $resource('http://localhost:8081/web/getPlayers',
                               { get: {
                method:"JSON",
                isArray:true,
                headers: { 
                    'Access-Control-Allow-Origin': '*'}
            }
        }
        );
        
        var players = GetPlayersResource.query();
        
        return players;
        
        };
        
}]);