// Services
fplApp
		.service(
				'fplService',
				[
						'$resource',
						'$http',
						'$log',
						'$location',
						function($resource, $http, $log, $location) {
							
							this.getConfiguration = function(){
								
								var GetResource = $resource(
										'http://' + $location.host() + ':8088/dreamteam-restserver/getConfiguration',
										{
											get : {
												method : "JSON",
												headers : {
													'Access-Control-Allow-Origin' : '*'
												}
											}
										});

								return GetResource.get();
								
							}
							
							this.getGameweeks = function(){
								
								var GetResource = $resource(
										'http://' + $location.host() + ':8088/dreamteam-restserver/getGameweeks',
										{
											get : {
												method : "JSON",
												isArray : true,
												headers : {
													'Access-Control-Allow-Origin' : '*'
												}
											}
										});

								var gameweeks = GetResource.query();
								
								return gameweeks;
								
							}
							
							this.getTeams = function(){
								
								var GetResource = $resource(
										'http://' + $location.host() + ':8088/dreamteam-restserver/getTeams',
										{
											get : {
												method : "JSON",
												isArray : true,
												headers : {
													'Access-Control-Allow-Origin' : '*'
												}
											}
										});

								var teams = GetResource.query();
								
								return teams;
								
							}

							this.getPlayers = function() {

								var GetPlayersResource = $resource(
										'http://' + $location.host() + ':8088/dreamteam-restserver/getPlayers',
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
										'http://' + $location.host() + ':8088/dreamteam-restserver/setSelected?fplId=' + player.getFplId() + '&selected=' + player.getSelected(),
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
										'http://' + $location.host() + ':8088/dreamteam-restserver/refreshPlayers',
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