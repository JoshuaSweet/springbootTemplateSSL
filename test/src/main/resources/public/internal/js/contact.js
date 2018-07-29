"use strict";

/****************
 * BUTTON SETUP *
 ****************/

/**
 * Setup contact send button so users can submit messages on page which will 
 * be at least stored in database for later reference.
 * @returns
 */
function setupContactSendBtn() {
	
	//bind on click
	$( '#contactSend' ).click( function( event ) {
		
		event.preventDefault();
		var clearHold = $( '#extra' ).val();
		
		if ( clearHold.length === 0 ) {
			
			var data = getContactData();
			var validData = validateContactData( data );
			
			if ( validData ) {
				
				postData( "api/contact/sendmsg/", data );
				clearContactData();
				
			}
			
		} else {
			
			/**
			 * Filter out some unwanted messages.
			 */
			
		}
				
	});
	
}

/********************
 * HELPER FUNCTIONS *
 ********************/

/**
 * Clears the contact input elements.
 * @returns
 */
function clearContactData() {
	
	$( '#contactEmail' ).val( "" );
	$( '#contactMessage' ).val( "" );
	
}

/**
 * Validates the contact data.
 * @param data
 * @returns
 */
function validateContactData( data ) {
	
	var email = data.conEmail;
	var msg = data.conMessage;
	
	if ( !ValidationUtil.isEmail( email ) ) {
		
		alert( "Email address invalid.  Please submit a valid email address." );
		return false;
		
	}

	if ( ValidationUtil.isEmpty( msg ) ) {
		
		alert( "Message cannot be blank.  Please submit a message." );
		return false;
		
	}
	
	return true;
	
}

/**
 * Creates data object from contact input.
 * @returns data object
 */
function getContactData() {
	
	var email = $( '#contactEmail' ).val();
	var msg = $( '#contactMessage' ).val();
	var data = { "conEmail":email, "conMessage":msg };
	return data;
	
}

/**
 * Submits ajax with dataObject.
 * @returns
 */
function postData( urlString, dataObject ) {
	
	var request = $.ajax({
		headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		  method: "POST",
		  url: urlString,
		  data: JSON.stringify( dataObject ),
		  dataType:'json'
		});
		  
	request.done( function( msg ) {
		alert( "Message sent." );
	});
	
	request.fail( function( msg ) {
		alert( "Something went wrong.  Please try again later." );
	});
	
}

