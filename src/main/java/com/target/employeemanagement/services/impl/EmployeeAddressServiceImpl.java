package com.target.employeemanagement.services.impl;

import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeAddressException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.models.EmployeeAddress;
import com.target.employeemanagement.services.EmployeeAddressConsumer;
import com.target.employeemanagement.services.EmployeeAddressProducer;
import com.target.employeemanagement.services.EmployeeAddressService;
import com.target.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeAddressProducer employeeAddressProducer;

    @Autowired
    private EmployeeAddressConsumer employeeAddressConsumer;

    /**
     *
     * @param employeeAddress Employee address.
     * @return Employee address
     * @throws EmployeeNotFoundException
     * @throws InvalidEmployeeAddressException
     */
    @Override
    public EmployeeAddress addEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeNotFoundException,
            InvalidEmployeeAddressException {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeAddress.getId());
        if(employee.isPresent()) {
            if(employeeAddress.isValidEmployeeAddress()) {
                return employeeAddressProducer.addAddress(employeeAddress);
            } else {
                throw new InvalidEmployeeAddressException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    /**
     *
     * @param id Employee ID.
     * @return Employee address
     */
    @Override
    public EmployeeAddress getEmployeeAddress(Integer id) {
        return employeeAddressConsumer.getAddress(id);
    }
}
