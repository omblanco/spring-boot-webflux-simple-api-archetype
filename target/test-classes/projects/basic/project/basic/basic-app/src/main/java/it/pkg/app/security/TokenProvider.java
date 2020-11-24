package it.pkg.app.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

import it.pkg.app.web.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

/**
 * Componente que genera los tokens de autenticación
 * @author oscar.martinezblanco
 *
 */
@AllArgsConstructor
@Component
public class TokenProvider implements Serializable {

    private PropertyResolver propertyResolver;
    
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    private static final String SIGNING_KEY_PROPERTY = "jwt.signing.key";
    private static final String AUTHORITIES_KEY = "scopes";
    private static final String ROLE_ADMIN_VALUE = "ROLE_ADMIN";
    
    private static final long serialVersionUID = 1L;

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(propertyResolver.getProperty(SIGNING_KEY_PROPERTY))
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDTO user) {
        //TODO: ROLES
        final List<String> authorities = Arrays.asList(ROLE_ADMIN_VALUE);
        
        return Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS256, propertyResolver.getProperty(SIGNING_KEY_PROPERTY))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }
}
