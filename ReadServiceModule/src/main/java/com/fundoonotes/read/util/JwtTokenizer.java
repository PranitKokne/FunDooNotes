package com.fundoonotes.read.util;

import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Purpose: The class contains a method which is used to retrieve the user id
 * from the token.
 * 
 * 
 * @author Pranit_Kokne
 * @version 1.0
 * @since 07-08-2018
 */

public class JwtTokenizer {

	private static final String APIKEY = "jaspreet";

	public static String getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(APIKEY)).parseClaimsJws(token)
				.getBody();
		return claims.getId();
	}
}
