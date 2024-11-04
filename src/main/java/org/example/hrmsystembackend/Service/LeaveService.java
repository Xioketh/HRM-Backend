package org.example.hrmsystembackend.Service;

import org.example.hrmsystembackend.DTO.LeaveDTO;
import org.springframework.http.ResponseEntity;

public interface LeaveService {
    ResponseEntity<?>saveLeave(LeaveDTO leaveDTO);
}
