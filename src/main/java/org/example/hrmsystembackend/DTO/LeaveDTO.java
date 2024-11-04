package org.example.hrmsystembackend.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.hrmsystembackend.Models.Employee;

@Getter
@Setter
public class LeaveDTO {
    private Long id;
    private Employee employee;
    private String startDate;
    private String endDate;
    private String leaveType;
    private String reason;
    private int status;
}
