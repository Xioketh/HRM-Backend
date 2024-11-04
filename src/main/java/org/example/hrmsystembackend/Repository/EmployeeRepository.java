package org.example.hrmsystembackend.Repository;

import org.example.hrmsystembackend.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT MAX(e.id) FROM Employee e")
    Long findMaxId();

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeId(String employeeId);
}
