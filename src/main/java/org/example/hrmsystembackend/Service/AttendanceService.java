package org.example.hrmsystembackend.Service;

import org.example.hrmsystembackend.DTO.AttendanceDTO;
import org.springframework.http.ResponseEntity;

public interface AttendanceService {

    ResponseEntity<?>saveAttendance(AttendanceDTO attendanceDTO);
}
