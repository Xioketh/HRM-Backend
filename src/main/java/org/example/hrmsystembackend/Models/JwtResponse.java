package org.example.hrmsystembackend.Models;

import lombok.Getter;

import java.util.List;

@Getter
public class JwtResponse {
    private String token;
    private Long id;
    private String empID;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long id, String empID, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.empID = empID;
        this.email = email;
        this.roles = roles;
    }
}

