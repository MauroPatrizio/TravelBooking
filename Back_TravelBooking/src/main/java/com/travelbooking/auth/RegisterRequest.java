package com.travelbooking.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The surname is required")
    private String surname;

    @Email(message = "The email must be valid")
    @NotBlank(message = "The email is required")
    private String email;

    @NotBlank(message = "The password is required")
    @Size(min = 6, max = 50, message = "The password must be between 6 and 50 characters long")
    private String password;

    @NotBlank(message = "The phone is required")
    private String phone;
}
