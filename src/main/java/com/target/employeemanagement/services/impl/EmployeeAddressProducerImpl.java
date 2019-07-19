package com.target.employeemanagement.services.impl;

import com.target.employeemanagement.models.EmployeeAddress;
import com.target.employeemanagement.services.EmployeeAddressProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAddressProducerImpl implements EmployeeAddressProducer {
    private static final String TOPIC = "employeeAddress";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public EmployeeAddress addAddress(EmployeeAddress employeeAddress) {
        String addressData = employeeAddress.getId() + "||||" + employeeAddress.getAddress();
        kafkaTemplate.send(TOPIC, addressData);
        return employeeAddress;
    }
}
