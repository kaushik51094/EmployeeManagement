package com.target.employeemanagement.controllers;

import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/hello")
    public String greet() {
        return "hi";
    }

    @PostMapping(value = "/add")
    public Employee createEmployee(@RequestBody Employee employee) throws InvalidEmployeeException {
        return employeeService.addEmployee(employee);
    }

    @PostMapping(value = "/addMultiple")
    public List<Employee> createEmployees(@RequestBody List<Employee> employees) throws InvalidEmployeeException {
        return employeeService.addEmployees(employees);
    }

    @GetMapping(value = "/get/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable(value = "id") String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping(value = "/update")
    public Employee updateEmployeeById(@RequestBody Employee emp) throws InvalidEmployeeException, EmployeeNotFoundException {
        return employeeService.updateEmployee(emp);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEmployeeById(@PathVariable(value = "id") String id) throws EmployeeNotFoundException {
        return employeeService.deleteEmployeeById(id);
    }

    @DeleteMapping(value = "/deleteAll")
    public String deleteAllEmployees() {
        return employeeService.deleteAllEmployees();
    }
}
