package org.example.hrmsystembackend.Service.Impl;

import org.example.hrmsystembackend.DTO.AttendanceDTO;
import org.example.hrmsystembackend.Models.Attendance;
import org.example.hrmsystembackend.Models.Employee;
import org.example.hrmsystembackend.Models.MessageResponse;
import org.example.hrmsystembackend.Repository.AttendanceRepository;
import org.example.hrmsystembackend.Repository.EmployeeRepository;
import org.example.hrmsystembackend.Service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> saveAttendance(AttendanceDTO attendanceDTO) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String currentDate = String.valueOf(LocalDate.now());
            String currentTime = LocalTime.now().format(formatter);

            Attendance attendance = null;
            Optional<Attendance> isAttendanceRecorded = attendanceRepository.findByEmployeeAndAttendanceDate(attendanceDTO.getEmployee(), currentDate);

            if (isAttendanceRecorded.isPresent()) {
                // out punch
                 attendance = isAttendanceRecorded.get();
                if (attendance.getOutTime() != null || TimeDifferenceCheck(attendance.getInTime(),currentTime)) {
                    return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Attendance Duplicated", attendance), HttpStatus.BAD_REQUEST);
                }

                attendance.setOutTime(currentTime);
                attendance.setStatus(1);
                attendanceRepository.save(attendance);
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "Attendance Out Time Saved Success", attendance), HttpStatus.OK);
            } else {
                // in punch
                attendanceDTO.setAttendanceDate(currentDate);
                attendanceDTO.setInTime(currentTime);
                Employee employee = employeeRepository.findByEmployeeId(attendanceDTO.getEmployee().getEmployeeId())
                        .orElseThrow(() -> new RuntimeException("Employee not found with id: " + attendanceDTO.getEmployee().getEmployeeId()));

                attendanceDTO.setEmployee(employee);
                attendanceDTO.setStatus(0);
                attendance = convertDtoToModel(attendanceDTO);
                attendanceRepository.save(attendance);
                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), "Success", "Attendance In Time Saved Success", attendance), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error", "Attendance Save Failed", null), HttpStatus.BAD_REQUEST);
        }
    }

    public Attendance convertDtoToModel(AttendanceDTO attendanceDTO) {
        return modelMapper.map(attendanceDTO, Attendance.class);
    }

    public boolean TimeDifferenceCheck(String inTime,String currentTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime inTimeObj = LocalTime.parse(inTime, formatter);
        LocalTime currentTimeObj = LocalTime.parse(currentTime, formatter);

        Duration duration = Duration.between(inTimeObj, currentTimeObj);
        long differenceInMinutes = duration.toMinutes();

        if (differenceInMinutes > 1) {
            return false;
        } else {
            return true;
        }
    }
}
