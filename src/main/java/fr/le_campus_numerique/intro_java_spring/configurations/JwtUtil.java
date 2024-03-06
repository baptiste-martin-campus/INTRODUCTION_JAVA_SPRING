package fr.le_campus_numerique.intro_java_spring.configurations;

import fr.le_campus_numerique.intro_java_spring.controllers.UserController;
import fr.le_campus_numerique.intro_java_spring.dao.UserRepository;
import fr.le_campus_numerique.intro_java_spring.entities.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    private static final long EXPIRATION_TIME = (24*60*60*1000); // 24 hours
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean isValid(String token){
        ProtectedJwt<JwsHeader, Claims> decryptedToken = parseToken(token);
        User user = userRepository.findByPseudo(decryptedToken.getPayload().getSubject());
        if (decryptedToken == null){
            LOGGER.error("No token found");
            return false;
        } else if(isExpired(decryptedToken.getPayload().getExpiration())) {
            LOGGER.error("Token validity expired... bad luck");
            return false;
        } else if (user.getPseudo() == null || !user.getPseudo().equals(decryptedToken.getPayload().getSubject())) {
            LOGGER.error("No user found with given token");
            return false;
        }
        return true;
    }

    private static boolean isExpired(Date expirationDate){
        Date currentDate = new Date();
        return currentDate.after(expirationDate);
    }

    public ProtectedJwt<JwsHeader, Claims> parseToken(String token){
        try {
            // Parse le token
            return Jwts.parser()
                    .setSigningKey(SECRET)  // Convertit la clé secrète en bytes
                    .build()
                    .parseSignedClaims(token);
        } catch(Exception e) {
            LOGGER.error("Error in token parsing : \n" + e);
        }
        return null;
    }

//    private SecretKey getSecretKey(){
//        byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);
//        SecretKey secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HS512");
//        return Keys.hmacShaKeyFor(secretKey.getEncoded());
//    }
}
