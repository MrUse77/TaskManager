/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siemens.task_manager.security;

import java.util.logging.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author agusd
 */
public class TokenUtils {
    
    private final static String ACCESS_TOKEN_SECRET = "EZijKeZMTgT4exOw8D4MYaBbSDpWqhhjcjJBXXVCf2vkEFKbRQ5OQPdtfsuVVnq09RbSVqmvgYB5MRDuvMilFvY3bd0s";
     private static final Logger logger = Logger.getLogger(JWTAuthorizationFilter.class.getName());
    
    public static String createToken(String mail, String id){
        Map<String,Object> extra = new HashMap<>();
        extra.put("mail",mail);
        
         return Jwts.builder().setClaims(extra).setSubject(id).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())).compact();
        
    }


        public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

        public Boolean validateToken(String token, SecurityUser userDetails) {
        try{
      final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }catch(Exception e){
            return false;
        }
    }
        
     public static UsernamePasswordAuthenticationToken getAuthentication(String token){
         try{
             
            Claims claims = Jwts.parserBuilder()
                            .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
                  
                 String password = claims.getSubject();
                 return new UsernamePasswordAuthenticationToken(password,null, Collections.emptyList());
        }catch(JwtException e){
            logger.log(Level.INFO, "tamos en el get",e);
            return null;
        }
        }
}