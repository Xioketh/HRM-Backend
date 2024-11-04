package org.example.hrmsystembackend.Service.Impl;

import org.example.hrmsystembackend.DTO.AttendanceDTO;
import org.example.hrmsystembackend.DTO.LeaveDTO;
import org.example.hrmsystembackend.Models.Attendance;
import org.example.hrmsystembackend.Models.Employee;
import org.example.hrmsystembackend.Models.Leave;
import org.example.hrmsystembackend.Models.MessageResponse;
import org.example.hrmsystembackend.Repository.EmployeeRepository;
import org.example.hrmsystembackend.Repository.LeaveRepository;
import org.example.hrmsystembackend.Service.LeaveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> saveLeave(LeaveDTO leaveDTO) {
        try {
            Employee employee = employeeRepository.findByEmployeeId(leaveDTO.getEmployee().getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + leaveDTO.getEmployee().getEmployeeId()));

            leaveDTO.setEmployee(employee);
            leaveDTO.setStatus(0);
            Leave leave = convertDtoToModel(leaveDTO);
            leaveRepository.save(leave);
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "Leave Saved Success", leave), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Leave Save Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    public Leave convertDtoToModel(LeaveDTO leaveDTO) {
        return modelMapper.map(leaveDTO, Leave.class);
    }
}
