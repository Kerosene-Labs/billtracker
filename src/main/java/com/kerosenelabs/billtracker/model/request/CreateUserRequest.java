package com.kerosenelabs.billtracker.model.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @Email
    private String email;

    @Length(min = 8, max = 64)
    private String password;
}
