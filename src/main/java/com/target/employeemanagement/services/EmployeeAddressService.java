package com.target.employeemanagement.services;

import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeAddressException;
import com.target.employeemanagement.models.EmployeeAddress;

public interface EmployeeAddressService {
    public EmployeeAddress addEmployeeAddress(EmployeeAddress employeeAddress) throws EmployeeNotFoundException, InvalidEmployeeAddressException;
    public EmployeeAddress getEmployeeAddress(Integer id);
}
