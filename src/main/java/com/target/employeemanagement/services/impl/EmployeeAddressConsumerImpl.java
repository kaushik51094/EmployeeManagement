package com.target.employeemanagement.services.impl;

import com.target.employeemanagement.models.EmployeeAddress;
import com.target.employeemanagement.services.EmployeeAddressConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeAddressConsumerImpl implements EmployeeAddressConsumer {
    private static final String TOPIC = "employeeAddress";

    private static Map<Integer, String> employeeAddressMap = new HashMap<Integer, String>();

    /**
     *
     * @param employeeAddress employee address
     */
    @KafkaListener(topics = TOPIC)
    public void consume(String employeeAddress) {
        int separatorIndex = employeeAddress.indexOf("||||");
        Integer id = Integer.valueOf(employeeAddress.substring(0, separatorIndex));
        String message = employeeAddress.substring(separatorIndex + 4);
        employeeAddressMap.put(id, message);
    }

    /**
     *
     * @param id employee ID.
     * @return Employee address
     */
    public EmployeeAddress getAddress(Integer id) {
        String address = employeeAddressMap.get(id);
        if(null != address) {
            EmployeeAddress employeeAddress = new EmployeeAddress();
            employeeAddress.setAddress(address);
            employeeAddress.setId(id);
            return employeeAddress;
        } else {
            return null;
        }
    }
}
