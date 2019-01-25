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
										'http://35.231.18.244:8088/dreamteam-restserver/getPlayers',
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
							
							this.toggleSelected = function(player) {
								
								player.setSelected(!player.getSelected());

								var PostPlayersResource = $resource(
										'http://35.231.18.244:8088/dreamteam-restserver/setSelected?fplId=' + player.getFplId() + '&selected=' + player.getSelected(),
										{
											save : {
												method : "POST",
												headers : {
													'Access-Control-Allow-Origin' : '*'
												}
											}
										});

								PostPlayersResource.save();
							};

							this.refreshPlayers = function() {

								var GetPlayersResource = $resource(
										'http://35.231.18.244:8088/dreamteam-restserver/refreshPlayers',
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