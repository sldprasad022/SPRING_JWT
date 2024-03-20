package in.nareshit.prasad.util;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil 
{
	@Value("$(app.secret)")
	private String secret;	
	
	byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
	
	//1.Generate Token
	public String generateToken(String subject)
	{
		return Jwts.builder()
		 		.setSubject(subject)
		 		.setIssuer("SLDPrasad")
		 		.setIssuedAt(new Date(System.currentTimeMillis()))
		 		.setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(15)))
		 		//.signWith(SignatureAlgorithm.HS512, secret.getBytes())
		 		.signWith(SignatureAlgorithm.HS512, keyBytes)
		 		.compact();
		 			 		
	}
	
	//2.Read Claims
	public Claims getClaims(String token)
	{
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				//.parseClaimsJws(token) it is not working in my laptop
				.parseClaimsJws(token)
				.getBody();
		
		
	}
	
	
	//3.Read Expiry Date
	public Date getExpDate(String token)
	{
		//return getClaims(token).getExpiration();
		return (Date) getClaims(token).getExpiration();
	}
	
	//4. Read Subject/UserName
	public String getUsername(String token)
	{
		return getClaims(token).getSubject();
	}
	
	//5.Validate Expiration Date
	public boolean isTokenExp(String token)
	{
		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	//6. Validate user name in token and database, expDate
	public boolean validateToken(String token, String username)
	{
		String tokenUserName = getUsername(token);
		return (username.equals(tokenUserName)&& !isTokenExp(token));
	}
}
