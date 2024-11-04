package org.example.hrmsystembackend.Security.Services;

import org.example.hrmsystembackend.Models.Employee;
import org.example.hrmsystembackend.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String empID) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmployeeId(empID)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + empID));

        return UserDetailsImpl.build(employee);
    }

}

