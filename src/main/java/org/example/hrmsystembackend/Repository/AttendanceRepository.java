package org.example.hrmsystembackend.Repository;

import org.example.hrmsystembackend.Models.Attendance;
import org.example.hrmsystembackend.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeAndAttendanceDate(Employee employee, String attendanceDate);

}
