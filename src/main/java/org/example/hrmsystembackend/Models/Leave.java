package org.example.hrmsystembackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "leave_records")
@Getter
@Setter
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId", nullable = false)
    private Employee employee;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String leaveType;

    private int status;
    private String reason;
}
