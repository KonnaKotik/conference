package ru.itis.conference.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.conference.form.SignUpForm;
import ru.itis.conference.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/signUp")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation("Create user")
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody SignUpForm signUpForm) {
        userService.signUp(signUpForm);
    }
}
