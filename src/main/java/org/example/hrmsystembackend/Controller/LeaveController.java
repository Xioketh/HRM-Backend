package org.example.hrmsystembackend.Controller;

import org.example.hrmsystembackend.DTO.LeaveDTO;
import org.example.hrmsystembackend.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/peoplesHrm/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping(value = "/saveLeave", produces = "application/json")
    public ResponseEntity<?> saveLeave(@RequestBody LeaveDTO leaveDTO) {
        return leaveService.saveLeave(leaveDTO);
    }
}
