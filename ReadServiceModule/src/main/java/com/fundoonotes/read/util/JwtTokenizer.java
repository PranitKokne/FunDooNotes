package com.fundoonotes.read.util;

import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenizer {

	private static final String APIKEY = "passKey";

	public static String getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(APIKEY)).parseClaimsJws(token)
				.getBody();
		return claims.getId();
	}
}
