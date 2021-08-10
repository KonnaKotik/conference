package ru.itis.conference.service;

import ru.itis.conference.dto.UserDto;
import ru.itis.conference.form.LoginForm;


public interface LoginService {

    UserDto login(LoginForm loginForm);
}
