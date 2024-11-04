package org.example.hrmsystembackend.Service;

import org.example.hrmsystembackend.DTO.EmployeeDTO;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<?>saveEmployee(EmployeeDTO employeeDTO);
    ResponseEntity<?>updateEmployee(EmployeeDTO employeeDTO);
    ResponseEntity<?>getAllEmployees();
    ResponseEntity<?>getEmployee(EmployeeDTO employeeDTO);
}
