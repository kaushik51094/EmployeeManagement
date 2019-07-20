package com.target.employeemanagement.services;

import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.models.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee addEmployee(Employee e) throws InvalidEmployeeException;

    public List<Employee> addEmployees(List<Employee> employees) throws InvalidEmployeeException;

    public Optional<Employee> getEmployeeById(Integer id);

    public List<Employee> getAllEmployees();

    public Employee updateEmployee(Employee e) throws EmployeeNotFoundException, InvalidEmployeeException;

    public String deleteEmployeeById(Integer id) throws EmployeeNotFoundException;

    public String deleteAllEmployees();

    public EmployeeDTO getEmployeeWithAddressById(Integer id) throws EmployeeNotFoundException;

    public List<EmployeeDTO> getAllEmployeesWithAddress();
}
