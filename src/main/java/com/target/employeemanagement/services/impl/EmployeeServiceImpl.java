package com.target.employeemanagement.services.impl;

import com.mongodb.BasicDBObject;
import com.target.employeemanagement.dao.EmployeeDao;
import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee addEmployee(Employee e) throws InvalidEmployeeException {
        if(e.isEmployeeValid()) {
            return employeeDao.insert(e);
        } else {
            throw new InvalidEmployeeException();
        }
    }

    @Override
    public List<Employee> addEmployees(List<Employee> employees) throws InvalidEmployeeException {
        boolean listContainsInvalidEmployee = false;
        Employee invalidEmployee = null;
        for(Employee emp : employees) {
            if(!emp.isEmployeeValid()) {
                listContainsInvalidEmployee = true;
                invalidEmployee = emp;
                break;
            }
        }
        if(!listContainsInvalidEmployee) {
            return employeeDao.insert(employees);
        } else {
            throw new InvalidEmployeeException(invalidEmployee + "is invalid");
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(String id) {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public Employee updateEmployee(Employee e) throws EmployeeNotFoundException, InvalidEmployeeException {
        Optional<Employee> employee = employeeDao.findById(e.getId());
        if(employee.isPresent()) {
            if(e.isEmployeeValid()) {
                employeeDao.save(e);
                return e;
            } else {
                throw new InvalidEmployeeException();
            }
        } else {
            throw new EmployeeNotFoundException("Employee with ID : " + e.getId() + " was not found");
        }
    }

    @Override
    public String deleteEmployeeById(String id) throws EmployeeNotFoundException {
        Optional<Employee> emp = employeeDao.findById(id);
        if(emp.isPresent()) {
            employeeDao.deleteById(id);
            return "Employee deleted successfully!";
        } else {
            throw new EmployeeNotFoundException("Employee with ID : " + id + " was not found!");
        }
    }

    @Override
    public String deleteAllEmployees() {
        employeeDao.deleteAll();
        return "All Employees deleted successfully!";
    }
}
