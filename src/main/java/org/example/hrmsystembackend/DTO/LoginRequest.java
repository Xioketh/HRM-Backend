package org.example.hrmsystembackend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String employeeId;

    @NotBlank
    private String password;
}
