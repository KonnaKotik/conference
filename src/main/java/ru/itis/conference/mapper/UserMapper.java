package ru.itis.conference.mapper;

import org.springframework.stereotype.Component;
import ru.itis.conference.form.SignUpForm;
import ru.itis.conference.form.UserCreateForm;
import ru.itis.conference.model.user.User;
import ru.itis.conference.model.user.UserRole;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserMapper {

    public User convertSignUpFormToUser(SignUpForm signUpForm) {
        return User.builder()
                .email(signUpForm.getEmail())
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .password(signUpForm.getPassword())
                .build();
    }

    public User convertUserCreateFormToUser(UserCreateForm userCreateForm) {
        return User.builder()
                .email(userCreateForm.getEmail())
                .firstName(userCreateForm.getFirstName())
                .lastName(userCreateForm.getLastName())
                .password(userCreateForm.getPassword())
                .userRole(UserRole.valueOf(userCreateForm.getRole()))
                .build();
    }

    public UserCreateForm convertUserToUserCreateForm(User user) {
        return UserCreateForm.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getUserRole().toString())
                .build();
    }

    private Stream<UserCreateForm> modelsToForm(List<User> users) {
        return users.stream().map(this::convertUserToUserCreateForm);
    }

    public List<UserCreateForm> convertUserToUserCreateFormList(List<User> users) {
        return modelsToForm(users).collect(Collectors.toList());
    }
}
