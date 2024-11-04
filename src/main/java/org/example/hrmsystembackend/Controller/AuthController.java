package org.example.hrmsystembackend.Controller;

import jakarta.validation.Valid;
import org.example.hrmsystembackend.DTO.LoginRequest;
import org.example.hrmsystembackend.Models.JwtResponse;
import org.example.hrmsystembackend.Models.MessageResponse;
import org.example.hrmsystembackend.Repository.EmployeeRepository;
import org.example.hrmsystembackend.Security.Jwt.JwtUtils;
import org.example.hrmsystembackend.Security.Services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmployeeId(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new JwtResponse(jwt,
                            userDetails.getId(),
                            userDetails.getEmpID(),
                            userDetails.getEmail(),
                            roles));
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Employee Credentials Wrong.", null), HttpStatus.BAD_REQUEST);
        }
    }
}
