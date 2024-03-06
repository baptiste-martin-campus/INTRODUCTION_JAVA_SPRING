package fr.le_campus_numerique.intro_java_spring.configurations;

import fr.le_campus_numerique.intro_java_spring.controllers.UserController;
import fr.le_campus_numerique.intro_java_spring.dao.UserRepository;
import fr.le_campus_numerique.intro_java_spring.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.ProtectedJwt;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            System.err.println("No Authorization header found");
            LOGGER.error("No Authorization header found");
        } else if (!authorizationHeader.startsWith("Bearer ")) {
            System.err.println("No Bearer found");
            LOGGER.error("No Bearer found");
        } else {
            String token = authorizationHeader.replace("Bearer ", "");
            if (jwtUtil.isValid(token)) {
                LOGGER.info("TOUT EST BON !");
                response.addHeader("Authorization-Status", "Token-Valid");
                ProtectedJwt<JwsHeader, Claims> decryptedToken = jwtUtil.parseToken(token);
                User user = userRepository.findByPseudo(decryptedToken.getPayload().getSubject());
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null,
                        user == null ? List.of() : user.getAuthorities());
                // Ajoute les informations de l’utilisateur
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Met à jour le contexte d’authentification
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
