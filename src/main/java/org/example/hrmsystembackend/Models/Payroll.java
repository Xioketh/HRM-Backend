package org.example.hrmsystembackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "payroll")
@Getter
@Setter
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employeeId", nullable = false)
    private Employee employee;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date paymentDate;

    @Column(nullable = false)
    private Double amountPaid;

    private String paymentStatus;
}
