package org.example.hrmsystembackend.Controller;

import org.example.hrmsystembackend.DTO.EmployeeDTO;
import org.example.hrmsystembackend.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@CrossOrigin(origins = "http://localhost:4500")
@RestController
@RequestMapping(value = "/api/peoplesHrm/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/saveEmployee", produces = "application/json")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.saveEmployee(employeeDTO);
    }

    @PutMapping(value = "/updateEmployee", produces = "application/json")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    @GetMapping(value = "/getAllEmployees", produces = "application/json")
    public ResponseEntity<?> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/getEmployee", produces = "application/json")
    public ResponseEntity<?> getEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.getEmployee(employeeDTO);
    }
}
