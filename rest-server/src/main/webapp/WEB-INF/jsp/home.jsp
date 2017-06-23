<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en-us">
<head>
<title>FPL</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta charset="UTF-8">

<style>
html, body, input, select, textarea {
	font-size: 1.05em !important;
}
</style>

<link rel="shortcut icon" href="favicon.ico">

<!-- load jquery via CDN -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>

<!-- load bootstrap via CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">

<script
	src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
    	$('#goalkeepersTable').DataTable({
    		"paging": false,
    		"info": false,
    		"order": [[ 2, "desc"]],
    		"searching": false
    	});
    	$('#defendersTable').DataTable({
    		"paging": false,
    		"info": false,
    		"order": [[ 2, "desc"]],
    		"searching": false
    	});
    	$('#midfieldersTable').DataTable({
    		"paging": false,
    		"info": false,
    		"order": [[ 2, "desc"]],
    		"searching": false
    	});
    	$('#forwardsTable').DataTable({
    		"paging": false,
    		"info": false,
    		"order": [[ 2, "desc"]],
    		"searching": false
    	});
    	$('#dreamTeamTable').DataTable({
    		"paging": false,
    		"info": false,
    		"order": [[ 2, "desc"]],
    		"searching": false
    	});
	});
</script>
</head>
<body>

	<div class="container" id="tabsContainer">

		<ul class="nav nav-tabs" id="tabMenu">
			<li id="Goalkeepers-tab" class="active"><a
				data-target="#goalkeepers" data-toggle="tab">Goalkeepers</a></li>
			<li id="Defenders-tab"><a data-target="#defenders"
				data-toggle="tab">Defenders</a></li>
			<li id="Midfielders-tab"><a data-target="#midfielders"
				data-toggle="tab">Midfielders</a></li>
			<li id="Forwards-tab"><a data-target="#forwards"
				data-toggle="tab">Forwards</a></li>
			<li id="dreamteam-tab"><a data-target="#dreamteam"
				data-toggle="tab">Dream Team</a></li>
		</ul>

		<div id="tabContent" class="tab-content">
			<div id="goalkeepers" class="tab-pane active">

				<table id="goalkeepersTable" class="table table-striped">

					<thead>
						<tr class="row">
							<th class="col-md-3">Player Name</th>
							<th class="col-md-2">Cost (M)</th>
							<th class="col-md-1">PPG</th>
							<th class="col-md-1">PPMPG</th>
							<th class="col-md-2">Std. Dev.</th>
							<th class="col-md-2">Brink of Suspension</th>
							<th class="col-md-3">Team Name</th>

						</tr>
					<thead>
					<tbody>
						<c:forEach var="player" items="${playerHelpCommand.goalkeepers}">
							<tr class="row">
								<td class="col-md-3">${player.name}</td>
								<td class="col-md-2">${player.nowCost}</td>
								<td class="col-md-1">${player.pointsPerGame}</td>
								<td class="col-md-1">${player.pointsPerGamePerMillion}</td>
								<td class="col-md-2">${player.standardDeviation}</td>
								<td class="col-md-2">${player.yellowCardBanAlert}</td>
								<td class="col-md-3">${player.team.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>

			<div id="defenders" class="tab-pane">
				<table id="defendersTable" class="table table-striped">

					<thead>
						<tr class="row">
							<th class="col-md-3">Player Name</th>
							<th class="col-md-2">Cost (M)</th>
							<th class="col-md-1">PPG</th>
							<th class="col-md-1">PPMPG</th>
							<th class="col-md-2">Std. Dev.</th>
							<th class="col-md-2">Brink of Suspension</th>
							<th class="col-md-3">Team Name</th>
						</tr>
					<thead>
					<tbody>
						<c:forEach var="player" items="${playerHelpCommand.defenders}">
							<tr class="row">
								<td class="col-md-3">${player.name}</td>
								<td class="col-md-2">${player.nowCost}</td>
								<td class="col-md-1">${player.pointsPerGame}</td>
								<td class="col-md-1">${player.pointsPerGamePerMillion}</td>
								<td class="col-md-2">${player.standardDeviation}</td>
								<td class="col-md-2">${player.yellowCardBanAlert}</td>
								<td class="col-md-3">${player.team.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div id="midfielders" class="tab-pane">
				<table id="midfieldersTable" class="table table-striped">

					<thead>
						<tr class="row">
							<th class="col-md-3">Player Name</th>
							<th class="col-md-2">Cost (M)</th>
							<th class="col-md-1">PPG</th>
							<th class="col-md-1">PPMPG</th>
							<th class="col-md-2">Std. Dev.</th>
							<th class="col-md-2">Brink of Suspension</th>
							<th class="col-md-3">Team Name</th>
						</tr>
					<thead>
					<tbody>
						<c:forEach var="player" items="${playerHelpCommand.midfielders}">
							<tr class="row">
								<td class="col-md-3">${player.name}</td>
								<td class="col-md-2">${player.nowCost}</td>
								<td class="col-md-1">${player.pointsPerGame}</td>
								<td class="col-md-1">${player.pointsPerGamePerMillion}</td>
								<td class="col-md-2">${player.standardDeviation}</td>
								<td class="col-md-2">${player.yellowCardBanAlert}</td>
								<td class="col-md-3">${player.team.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div id="forwards" class="tab-pane">
				<table id="forwardsTable" class="table table-striped">

					<thead>
						<tr class="row">
							<th class="col-md-3">Player Name</th>
							<th class="col-md-2">Cost (M)</th>
							<th class="col-md-1">PPG</th>
							<th class="col-md-1">PPMPG</th>
							<th class="col-md-2">Std. Dev.</th>
							<th class="col-md-2">Brink of Suspension</th>
							<th class="col-md-3">Team Name</th>
						</tr>
					<thead>
					<tbody>
						<c:forEach var="player" items="${playerHelpCommand.forwards}">
							<tr class="row">
								<td class="col-md-3">${player.name}</td>
								<td class="col-md-2">${player.nowCost}</td>
								<td class="col-md-1">${player.pointsPerGame}</td>
								<td class="col-md-1">${player.pointsPerGamePerMillion}</td>
								<td class="col-md-2">${player.standardDeviation}</td>
								<td class="col-md-2">${player.yellowCardBanAlert}</td>
								<td class="col-md-3">${player.team.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>