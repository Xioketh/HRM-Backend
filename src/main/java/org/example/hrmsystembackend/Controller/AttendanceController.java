package org.example.hrmsystembackend.Controller;

import org.example.hrmsystembackend.DTO.AttendanceDTO;
import org.example.hrmsystembackend.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/peoplesHrm/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(value = "/saveAttendance", produces = "application/json")
    public ResponseEntity<?> saveAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        return attendanceService.saveAttendance(attendanceDTO);
    }
}
