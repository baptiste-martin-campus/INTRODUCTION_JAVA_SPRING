package fr.le_campus_numerique.intro_java_spring.controllers;

import fr.le_campus_numerique.intro_java_spring.configurations.JwtUtil;
import fr.le_campus_numerique.intro_java_spring.dto.AuthenticationParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JwtUtil jwt;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationParams authenticationParams) {
        LOGGER.info("Tentative de connexion");
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationParams.username(), authenticationParams.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwt.generateToken(authenticationParams.username());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            LOGGER.info("Connexion réussie");
            return new ResponseEntity<>("User login successfully ! Token : \n" +token, headers, HttpStatus.OK);
        } catch( Exception err){
            LOGGER.error(String.valueOf(err));
            return new ResponseEntity<>("Erreur lors de la connexion : \n" + err, HttpStatus.UNAUTHORIZED);
        }
    }
}
