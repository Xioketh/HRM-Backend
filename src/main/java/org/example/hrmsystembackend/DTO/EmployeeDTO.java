package org.example.hrmsystembackend.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.hrmsystembackend.Models.Department;
import org.example.hrmsystembackend.Models.Role;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;
    private Double salary;
    private Department department;
    private Date hireDate;
    private int status;
    private String password;
    private Set<Role> roles;
    private int role_id;
}
