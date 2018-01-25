// Controller
fplApp.controller('fplController', ['$scope', '$log', '$routeParams', 'fplService', function($scope, $log, $routeParams, fplService){

	
	$scope.sortType = 'getPointsPerGame()';
	$scope.sortDirectionIsAscending = true;
	
	
	
	$scope.$watch('sortType', function(newValue, oldValue){
		console.log('oldValue: ' + oldValue);
		console.log('newValue: ' + newValue);
		console.log('sortDirectionIsAscending: ' + $scope.sortDirectionIsAscending);
	});
	
	$scope.players = {};
	$scope.slider = {
		    minValue: 0,
		    maxValue: 38,
		    options: {
		        floor: 0,
		        ceil: 38,
		        step: 1,
		        showSelectionBar: true,
		        showTicksValues: 5,
		        onChange: function() {
		            $scope.fplFilter.minGameweek = $scope.slider.minValue;
		            $scope.fplFilter.maxGameweek = $scope.slider.maxValue;
		        }
		    }
		};
	$scope.fplFilter = {
			'minGameweek' : 0,
			'maxGameweek' : 38
			
	};
	
	$scope.refreshPlayers = function(){
		$scope.players = fplService.refreshPlayers();
		
		$scope.players
			.$promise
				.then(
						// on success
						function(response){
							
							for(var playerId = 0; playerId < $scope.players.length; playerId++){
								var player = new Player($scope.players[playerId]);
								
								player.positionFullName = $scope.players[playerId].position.fullName;
								
								$scope.players[playerId] = player;
							}
						}
				);
	}
	
	$scope.setSortType = function(sortType, sortDirectionIsAscending){
		console.log("sortType: " + sortType);
		console.log("sortDirectionIsAscending: " + sortDirectionIsAscending);
		if(sortType === $scope.sortType){
			console.log("sortType: " + sortType + " equals: " + $scope.sortType)
			$scope.sortDirectionIsAscending = !sortDirectionIsAscending;
		} else {
			$scope.sortType = sortType;
			$scope.sortDirectionIsAscending = true;
		}
		
		
	};
	
	function Player(data) {
		this.data = data;
		
		this.getId = function(){
			return this.data.id;
		}
		
		this.getTeamName = function(){
			return this.data.team.name;
		}
		
		this.getName = function(){
			return this.data.name;
		}
		
		this.getNowCost = function(){
			return this.data.nowCost;
		}
		
		this.getPointsPerGame = function(){
			var gameweeks = this.getGameweeksStatistics();
			
			var totalPoints = 0;
			var totalGames = 0;
			
			for(var gameweekId = 0; gameweekId < gameweeks.length; gameweekId++){
				var gameweek = gameweeks[gameweekId];
				
				totalPoints = totalPoints + gameweek.totalPoints;
				totalGames = totalGames + 1;
			}
			
			// TODO: potential divide zero bug
			var pointsPerGame = totalPoints / totalGames;
			
			var rounded = pointsPerGame.toFixed(2);
			
			return (rounded > -1) ? rounded : 0;
		}
		
		this.getPointsPerGamePerMillion = function(){
			var pointsPerGame = this.getPointsPerGame();
			
			// TODO: potential divide zero bug
			return (pointsPerGame / this.getNowCost()).toFixed(2);
		}
		
		this.getTotalGames = function(){
			var gameweeks = this.getGameweeksStatistics();
			
			var totalGames = 0;
			
			for(var gameweekId = 0; gameweekId < gameweeks.length; gameweekId++){
				var gameweek = gameweeks[gameweekId];
				
				if(gameweek.minutes > 30){
					totalGames = totalGames + 1;	
				}
			}
			
			return totalGames;
		}
		
		this.getGameweeksStatistics = function(){
			var gameweeks = data.gameweeksStatistics;
			
			var filteredGameweeks = [];
			
			for(var gameweekId = 0; gameweekId < gameweeks.length; gameweekId++){
				if(gameweeks[gameweekId].gameweek >= $scope.fplFilter.minGameweek && gameweeks[gameweekId].gameweek <= $scope.fplFilter.maxGameweek)
				filteredGameweeks.push(gameweeks[gameweekId]);
			}
			
			return filteredGameweeks;
		}
		
		this.getPositionFullName = function(){
			return this.data.position.fullName;
		}
		
		this.getFixtures = function(){
			return this.data.fixtures;
		}
		
		this.getLikelihoodOfPriceChange = function(){
			return this.data.likelihoodOfPriceChange;
		}
		
		this.getLikelihoodOfPriceChangeWithoutSign = function(){
			if(this.data.likelihoodOfPriceChange < 0){
				return this.data.likelihoodOfPriceChange * -1;
			} else {
				return this.data.likelihoodOfPriceChange;	
			}
		}
		
	}
	
	$scope.getPlayers = function(){
		$scope.players = fplService.getPlayers();
		
		$scope.players
			.$promise
				.then(
						// on success
						function(response){
							
							for(var playerId = 0; playerId < $scope.players.length; playerId++){
								var player = new Player($scope.players[playerId]);
								
								player.positionFullName = $scope.players[playerId].position.fullName;
								
								$scope.players[playerId] = player;
							}
						}
				);
	}
	
	$scope.getPlayers();
	
}]);