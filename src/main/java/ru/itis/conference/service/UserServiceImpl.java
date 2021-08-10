package ru.itis.conference.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.conference.form.SignUpForm;
import ru.itis.conference.mapper.UserMapper;
import ru.itis.conference.model.user.User;
import ru.itis.conference.model.user.UserRole;
import ru.itis.conference.model.user.UserState;
import ru.itis.conference.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    public void signUp(SignUpForm signUpForm) {
        if(!userRepository.existsByEmail(signUpForm.getEmail())) {
            signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
            User user = userMapper.convertSignUpFormToUser(signUpForm);
            user.setUserRole(UserRole.LISTENER);
            user.setUserState(UserState.ACTIVE);
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Email is already used");
        }
    }
}
