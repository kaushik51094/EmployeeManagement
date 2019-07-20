package com.target.employeemanagement.controllers;

import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.models.EmployeeDTO;
import com.target.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public List<Employee> createEmployees(@RequestBody List<Employee> employees) throws InvalidEmployeeException {
        return employeeService.addEmployees(employees);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping
    public String deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }

    @GetMapping(value = "/withAddress")
    public List<EmployeeDTO> getAllEmployeesWithAddress() {
        return employeeService.getAllEmployeesWithAddress();
    }
}
