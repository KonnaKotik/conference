package ru.itis.conference.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.conference.form.SignUpForm;
import ru.itis.conference.mapper.UserMapper;
import ru.itis.conference.model.user.User;
import ru.itis.conference.repository.UserRepository;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void signUp() {
        SignUpForm signUpForm = new SignUpForm();

        Mockito.doReturn(new User())
                .when(userMapper)
                .convertSignUpFormToUser(signUpForm);

        boolean isUserSignUp = userService.signUp(signUpForm);

        Assert.assertTrue(isUserSignUp);
    }

    @Test(expected = BadCredentialsException.class)
    public void signUpFailTest(){
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("john123@123.com");

        Mockito.doReturn(true)
                .when(userRepository)
                .existsByEmail("john123@123.com");

        boolean isUserSignUp = userService.signUp(signUpForm);

        Assert.assertFalse(isUserSignUp);
    }

}