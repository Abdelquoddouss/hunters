package com.java.hunters.web.vm;

import com.java.hunters.domain.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVM {

    @NotNull(message = "you must enter the role of the user")
    private String username;

    @NotNull(message = "you must enter the role of the user")
    private Role role;

    @NotNull(message = "you must enter the first name of the user")
    private String firstName;

    @NotNull(message = "you must enter the last name of the user")
    private String lastName;

    @NotNull(message = "you must enter the first name of the user")
    private String cin;

    @NotNull(message = "you must enter the email of the user")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "you must enter the password of the user")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Password must be at least 8 characters long, and contain at least one uppercase letter, one lowercase letter, and one number"
    )

    private String password;

    @NotNull(message = "you must enter the nationality me of the user")
    private String nationality;

    @NotNull(message = "you must enter the licenseExpirationDate  me of the user")
    @Future(message = "The license expiration date must be in the future")
    private LocalDateTime licenseExpirationDate;
}
