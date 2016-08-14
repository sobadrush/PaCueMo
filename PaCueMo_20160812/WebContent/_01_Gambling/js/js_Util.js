/**
 *    別人寫的 Javascript timeStamp()
 */

function timeStamp() {
		// Create a date object with the current time
		  var now = new Date();

		// Create an array with the current month, day and time
		  var date = [ now.getFullYear() , now.getMonth() + 1, now.getDate() ];

		// Create an array with the current hour, minute and second
		  var time = [ now.getHours(), now.getMinutes(), now.getSeconds() ];

		// Determine AM or PM suffix based on the hour  //-------------------------  顯示 am pm
		  var suffix = ( time[0] < 12 ) ? "AM" : "PM";

		// Convert hour from military time
//		  time[0] = ( time[0] < 12 ) ? time[0] : time[0] - 12; //-------------------------  12小時制

		// If hour is 0, set it to 12  //-------------------------  12小時制
//		  time[0] = time[0] || 12;

		// If seconds and minutes are less than 10, add a zero
		  for ( var i = 1; i < 3; i++ ) {
		    if ( time[i] < 10 ) {
		      time[i] = "0" + time[i];
		    }
		  }

		// Return the formatted string
//		  return date.join("-") + " " + time.join(":") + " " + suffix;
		  return date.join("-") + " " + time.join(":") + " ";
		}




