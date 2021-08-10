package ru.itis.conference.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.conference.security.authentication.JwtTokenAuthentication;
import ru.itis.conference.security.details.UserDetailsImpl;


@Component
public class JwtTokenAuthProvider implements AuthenticationProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtTokenAuthentication jwtTokenAuthentication = (JwtTokenAuthentication) authentication;
        Claims body;
        try {
            body = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwtTokenAuthentication.getName())
                    .getBody();
        } catch (Exception e){
            throw new AuthenticationServiceException("Invalid body");
        }

        UserDetails userDetails = new UserDetailsImpl(
                Long.parseLong(body.get("sub").toString()),
                body.get("role").toString(),
                body.get("email").toString()

        );

        jwtTokenAuthentication.setUserDetails(userDetails);
        jwtTokenAuthentication.setAuthenticated(true);

        return jwtTokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtTokenAuthentication.class.equals(authentication);
    }
}
