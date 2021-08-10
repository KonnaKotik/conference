package ru.itis.conference.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
