package org.example.hrmsystembackend.Service.Impl;

import org.example.hrmsystembackend.DTO.EmployeeDTO;
import org.example.hrmsystembackend.Models.Employee;
import org.example.hrmsystembackend.Models.MessageResponse;
import org.example.hrmsystembackend.Models.Role;
import org.example.hrmsystembackend.Repository.EmployeeRepository;
import org.example.hrmsystembackend.Repository.RoleRepository;
import org.example.hrmsystembackend.Service.EmployeeService;
import org.example.hrmsystembackend.Utill.IdFormatUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCEncoder;

    @Override
    public ResponseEntity<?> saveEmployee(EmployeeDTO employeeDTO) {
        Optional<Employee> isEmployee = employeeRepository.findByEmail(employeeDTO.getEmail());

        if (isEmployee.isPresent()) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Email is already in use. Please try another email.", null), HttpStatus.BAD_REQUEST);
        }


        Long lastEmployeeId = employeeRepository.findMaxId();
        String nextEmpId = IdFormatUtil.generateId(lastEmployeeId, 5);

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findById(employeeDTO.getRole_id())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(role);

        employeeDTO.setEmployeeId(nextEmpId);
        employeeDTO.setPassword(bCEncoder.encode(nextEmpId.toLowerCase()+"@"+ Year.now().getValue()));
        employeeDTO.setRoles(roles);
        Employee employee = convertDtoToModel(employeeDTO);
        employeeRepository.save(employee);

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "Employee Saved Success", employee), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateEmployee(EmployeeDTO employeeDTO) {
        String employeeId = employeeDTO.getEmployeeId();
        Optional<Employee> isEmployee = employeeRepository.findByEmployeeId(employeeId);

        if (!(isEmployee.isPresent())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Employee Not Present.", null), HttpStatus.BAD_REQUEST);
        }

        Employee employee = isEmployee.get();
        employee.setPhone(employeeDTO.getPhone());
        employee.setSalary(employeeDTO.getSalary());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setStatus(employeeDTO.getStatus());
        employeeRepository.save(employee);

        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "Employee Update Success", employee), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "All Employees Fetched", employees), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getEmployee(EmployeeDTO employeeDTO) {
        String employeeId = employeeDTO.getEmployeeId();
        Optional<Employee> isEmployee = employeeRepository.findByEmployeeId(employeeId);
        if (isEmployee.isPresent()) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "Employee Fetched.", isEmployee.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Employee Not Present.", null), HttpStatus.BAD_REQUEST);
    }

    public Employee convertDtoToModel(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
}
