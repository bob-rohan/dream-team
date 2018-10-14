// Services
fplApp
		.service(
				'fplService',
				[
						'$resource',
						'$http',
						'$log',
						function($resource, $http, $log) {

							this.getPlayers = function() {

								var GetPlayersResource = $resource(
										'http://35.231.44.185:8088/dreamteam-restserver/getPlayers',
										{
											get : {
												method : "JSON",
												isArray : true,
												headers : {
													'Access-Control-Allow-Origin' : '*'
												}
											}
										});

								var players = GetPlayersResource.query();

								return players;

							};

							this.refreshPlayers = function() {

								var GetPlayersResource = $resource(
										'http://35.231.44.185:8088/dreamteam-restserver/refreshPlayers',
										{
											get : {
												method : "JSON",
												isArray : true,
												headers : {
													'Access-Control-Allow-Origin' : '*'
												}
											}
										});

								var players = GetPlayersResource.query();

								return players;

							};

						} ]);