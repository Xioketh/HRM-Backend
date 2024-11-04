package org.example.hrmsystembackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "attendance")
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employee;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String attendanceDate;

    @Temporal(TemporalType.DATE)
    private String inTime;

    @Temporal(TemporalType.DATE)
    private String outTime;

    @Column(nullable = false)
    private int status;
}
