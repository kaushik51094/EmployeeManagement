package com.target.employeemanagement.controllers;

import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.models.EmployeeDTO;
import com.target.employeemanagement.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    public EmployeeController() { }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) throws InvalidEmployeeException {
        return employeeService.addEmployee(employee);
    }

    @GetMapping(value = "/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable(value = "id") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping
    public Employee updateEmployeeById(@RequestBody Employee emp) throws InvalidEmployeeException, EmployeeNotFoundException {
        return employeeService.updateEmployee(emp);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteEmployeeById(@PathVariable(value = "id") Integer id) throws EmployeeNotFoundException {
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping(value = "/withAddress/{id}")
    public EmployeeDTO getEmployeeWithAddress(@PathVariable(value = "id") Integer id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeWithAddressById(id);
    }
}
