package org.example.hrmsystembackend.Utill;

import org.springframework.stereotype.Component;

@Component
public class IdFormatUtil {

    public static String generateId(Long lastEmpID, int numOfDigits) {
        Long newEmpID = lastEmpID == null ? (1) : (lastEmpID+ 1);
        String formattedEmpID = String.format("%0" + numOfDigits + "d", newEmpID);
        return "EMP" + formattedEmpID;
    }
}
