package ru.itis.conference.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.conference.dto.TokenDto;
import ru.itis.conference.dto.UserDto;
import ru.itis.conference.form.LoginForm;
import ru.itis.conference.model.user.User;
import ru.itis.conference.repository.UserRepository;

import javax.persistence.EntityExistsException;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.prefix}")
    private String prefix;

    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String getTokenAsString(User user) {
        return prefix + " " + Jwts.builder()
                .claim("role", user.getUserRole().toString())
                .claim("email", user.getEmail())
                .setSubject(user.getId().toString())
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public UserDto login(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail()).orElseThrow(EntityExistsException::new);

        if(passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
           TokenDto token = TokenDto.builder().token(getTokenAsString(user)).build();
           return new UserDto(user.getEmail(), user.getFirstName(), token.getToken());
        } else {
            throw new BadCredentialsException("Incorrect login/password");
        }
    }
}
