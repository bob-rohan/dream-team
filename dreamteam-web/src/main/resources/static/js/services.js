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
										'http://dreamteam:8088/dreamteam-restserver/getPlayers',
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
										'http://192.168.0.7:8088/dreamteam-restserver/refreshPlayers',
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