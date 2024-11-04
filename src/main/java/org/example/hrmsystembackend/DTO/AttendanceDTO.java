package org.example.hrmsystembackend.DTO;

import lombok.Getter;
import lombok.Setter;
import org.example.hrmsystembackend.Models.Employee;

@Getter
@Setter
public class AttendanceDTO {
    private Long id;
    private Employee employee;
    private String attendanceDate;
    private String inTime;
    private String outTime;
    private int status;
}
