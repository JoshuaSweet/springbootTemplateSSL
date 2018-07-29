"use strict";

/**
 * Constructor
 * @returns
 */
function ValidationUtil() {
	
}

/**
 * Uses regex to determine if string represent valid email address.  Not perfect.
 * @param email
 * @returns
 */
ValidationUtil.isEmail = function( email ) {
	
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return regex.test( email );
	
}

ValidationUtil.isEmpty = function( string ) {
	
	return typeof( string ) === "undefined" || string === null || string.length === 0;
	
}