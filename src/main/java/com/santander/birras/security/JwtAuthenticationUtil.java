package com.santander.birras.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtAuthenticationUtil {

    public String generateJwtToken(UserDetails userDetails) {
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(userDetails.getUsername()).
                setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 10*60*60*1000)).
                signWith(SignatureAlgorithm.HS256, "SECRET").compact();
    }

    public String decodeUsername(String jwt) {
        return Jwts.parser().setSigningKey("SECRET").parseClaimsJws(jwt).getBody().getSubject();
    }

    public Boolean isExpired(String jwt) {
        return Jwts.parser().setSigningKey("SECRET").parseClaimsJws(jwt).getBody().getExpiration().before(new Date());
    }

    public Boolean validateJwtToken(String jwt, UserDetails userDetails) {
        final String username = decodeUsername(jwt);
        return (!isExpired(jwt) && (username.equalsIgnoreCase(userDetails.getUsername())));
    }
}
