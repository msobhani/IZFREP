// next two lines for JS-Lint
"use strict";
var $, navigator, alert, document;

// create our namespace
var RocknCoder = RocknCoder || {};

var bearing = 0;
// event handlers for the compass stuff,
// one for updating the header text
// the other for rotating the compass
RocknCoder.Compass = (function() {
	
	navigator.geolocation.getCurrentPosition(onSuccess, onError);
	
	
	var lastHeading = -1,
	// cache the jQuery selectors
	$headText = $("header > h1"), $compass = $("#compass"), $qibla = $("#qibla"),
	// displays the degree
	updateHeadingText = function(event, heading) {
		event.preventDefault();
		//$headText.html(heading + "&deg;");
		return false;
	},
	// adjusts the rotation of the compass
	updateCompass = function(event, heading) {
		event.preventDefault();
		// to make the compass dial point the right way
		var rotation = 360 - heading, rotateDeg = 'rotate('
				+ rotation + 'deg)';
		// TODO: fix - this code only works on webkit browsers, not wp7
		$compass.css('-webkit-transform', rotateDeg);
		
		 rotation = 180 - (heading + bearing), rotateDeg = 'rotate('
			+ rotation + 'deg)';
		 $qibla.css('-webkit-transform', rotateDeg);
		return false;
	};
	// bind both of the event handlers to the "newHeading" event
	$("body").bind("newHeading", updateCompass).bind("newHeading",
			updateHeadingText);
}());

// hook the compass watch
// normally I would un-hook an event, but this is a quick tutorial
document.addEventListener('deviceready', function() {
	
	RocknCoder.Compass.watchId = navigator.compass.watchHeading(function(
			heading) {
		// only magnetic heading works universally on iOS and Android
		// round off the heading then trigger newHeading event for any listeners
		var newHeading = Math.round(heading.magneticHeading);
		$("body").trigger("newHeading", [ newHeading ]);
	}, function(error) {
		// if we get an error, show its code
		alert("Compass error: " + error.code);
	}, {
		frequency : 100
	});



}

);

function onSuccess(position) {

	var lon1 = -39.75 * Math.PI / 180;
	var lon2 = position.coords.longitude * Math.PI / 180;

	var lat1 = 21.45 * Math.PI / 180;
	var lat2 = position.coords.latitude * Math.PI / 180;

	var lonDelta = (lon2 - lon1);
	var y = Math.sin(lonDelta) * Math.cos(lat2);
	var x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2)
			* Math.cos(lonDelta);
	bearing = Math.atan2(y, x) * 180 / Math.PI;

	alert("Bearing: " + bearing);
	
	//displayMonth(0, position.coords.latitude, position.coords.longitude);
	
}

function onError(error) {
	alert('code: ' + error.code + '\n' + 'message: ' + error.message + '\n');
}







var currentDate = new Date();
var timeFormat = 1; 

// display monthly timetable
function displayMonth(offset, lat, long) {

	var timeZone = 2;
	var dst = 'auto';
	var method = 'Tehran';

	prayTimes.setMethod(method);
	
	
	var day = currentDate.getDay();
	var month = currentDate.getMonth();
	var year = currentDate.getFullYear();
	var title = monthFullName(month)+ ' '+ year;	
	
	navigator.geolocation.getCurrentPosition(onSuccess, onError);
	
	makeTable(year, month, day, lat, lng, timeZone, dst);
}

// make monthly timetable
function makeTable(year, month, lat, lng, timeZone, dst) {
	var $timetable = $("#timetable"); 
	var items = {title: 'Event', time: 'Zeit'};
			
	var tbody = document.createElement('tbody');
	tbody.appendChild(makeTableRow(items, items, 'head-row'));

	var date = new Date(year, month, day);
	var format = '24h';
	
	var times = prayTimes.getTimes(date, [lat, lng], timeZone, dst, format);	
	
	tbody.appendChild(makeTableRow('Day',times[0], klass));
	tbody.appendChild(makeTableRow('Fajr',times[1], klass));
	tbody.appendChild(makeTableRow('Sunrise',times[2], klass));
	tbody.appendChild(makeTableRow('Dhuhr',times[3], klass));
	tbody.appendChild(makeTableRow('Asr',times[4], klass));
	tbody.appendChild(makeTableRow('Maghrib',times[5], klass));
	tbody.appendChild(makeTableRow('Isha',times[6], klass));
	
	
	removeAllChild($timetable);
	$timetable.appendChild(tbody);
}

// make a table row
function makeTableRow(title, data, klass) {
	var row = document.createElement('tr');
	
		var cell = document.createElement('td');
		cell.innerHTML = title;
		cell.style.width = '10.5em' ;
		row.appendChild(cell);
		
		cell = document.createElement('td');
		cell.innerHTML = data;
		cell.style.width = '3.7em' ;
		row.appendChild(cell);
	
		
		
	
	row.className = klass;
	return row;		
}
// remove all children of a node
function removeAllChild(node) {
	if (node == undefined || node == null)
		return;

	while (node.firstChild)
		node.removeChild(node.firstChild);
}



// return month full name
function monthFullName(month) {
	var monthName = new Array('January', 'February', 'March', 'April', 'May', 'June', 
					'July', 'August', 'September', 'October', 'November', 'December');
	return monthName[month];
}


