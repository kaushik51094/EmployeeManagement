package com.target.employeemanagement.controllers;

import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeAddressException;
import com.target.employeemanagement.models.EmployeeAddress;
import com.target.employeemanagement.services.EmployeeAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employeeAddress")
public class EmployeeAddressController {

    @Autowired
    private EmployeeAddressService employeeAddressService;

    @PostMapping(value = "/add")
    public EmployeeAddress addEmployeeAddress(@RequestBody EmployeeAddress employeeAddress)
            throws EmployeeNotFoundException, InvalidEmployeeAddressException {
        return employeeAddressService.addEmployeeAddress(employeeAddress);
    }

    @GetMapping(value = "/get/{id}")
    public EmployeeAddress getEmployeeAddress(@PathVariable(value = "id") Integer id) {
        return employeeAddressService.getEmployeeAddress(id);
    }

}
