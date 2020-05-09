package com.project.tb.security;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.project.tb.models.User;
import static com.project.tb.security.SecurityConstants.EXPIRATION_TIME;
import static com.project.tb.security.SecurityConstants.SECRET;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
@Component
public class JwtTokenProvider {
//generating the token
	public String generateToken(Authentication authentication) {
		User user=(User) authentication.getPrincipal();
		Date now=new Date(System.currentTimeMillis());
		Date expiryDate=new Date(now.getTime()+EXPIRATION_TIME);
		String userId=Long.toString(user.getUserId()); //we converted it to string because the token is a string
		Map<String, Object> claims=new HashMap<String, Object>();
		claims.put("id", userId);
		claims.put("email", user.getEmail());
		claims.put("name",user.getName());
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
	}
	
	//Validating a Token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			System.out.println("invalid token signature");
		}catch (MalformedJwtException ex) {
			System.out.println("Malformed token");
		}catch (ExpiredJwtException ex) {
			System.out.println("Expired token");
		}catch (IllegalArgumentException ex) {
			System.out.println("claims map is empty");
		}
		return false;
	}
	//get user Id from a token so we can use it in the jwt filters after that
	public Long getUserIdFromJwt(String token) {
		Claims claims=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		String idString=(String)claims.get("id");
		return Long.parseLong(idString);
	}
}
