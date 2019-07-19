package com.target.employeemanagement.services;

import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee addEmployee(Employee e) throws InvalidEmployeeException;

    public List<Employee> addEmployees(List<Employee> employees) throws InvalidEmployeeException;

    public Optional<Employee> getEmployeeById(String id);

    public List<Employee> getAllEmployees();

    public Employee updateEmployee(Employee e) throws EmployeeNotFoundException, InvalidEmployeeException;

    public String deleteEmployeeById(String id) throws EmployeeNotFoundException;

    public String deleteAllEmployees();
}
