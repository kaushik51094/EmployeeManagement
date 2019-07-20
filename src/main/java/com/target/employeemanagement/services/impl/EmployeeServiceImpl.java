package com.target.employeemanagement.services.impl;

import com.mongodb.BasicDBObject;
import com.target.employeemanagement.dao.EmployeeDao;
import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.models.EmployeeAddress;
import com.target.employeemanagement.models.EmployeeDTO;
import com.target.employeemanagement.services.EmployeeAddressService;
import com.target.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeAddressService employeeAddressService;

    /**
     *
     * @param e employee.
     * @return Employee
     * @throws InvalidEmployeeException
     */
    @Override
    public Employee addEmployee(Employee e) throws InvalidEmployeeException {
        if(e.isEmployeeValid()) {
            return employeeDao.insert(e);
        } else {
            throw new InvalidEmployeeException();
        }
    }

    /**
     *
     * @param employees List of employees.
     * @return List of employees
     * @throws InvalidEmployeeException
     */
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

    /**
     *
     * @param id employee ID.
     * @return employee
     */
    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeDao.findById(id);
    }

    /**
     *
     * @return list of employees.
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    /**
     *
     * @param e employee
     * @return employee
     * @throws EmployeeNotFoundException
     * @throws InvalidEmployeeException
     */
    @Override
    public Employee updateEmployee(Employee e) throws EmployeeNotFoundException, InvalidEmployeeException {
        Optional<Employee> employee = employeeDao.findById(e.getId());
        if(employee.isPresent()) {
            if(e.isEmployeeValid()) {
                return employeeDao.save(e);
            } else {
                throw new InvalidEmployeeException();
            }
        } else {
            throw new EmployeeNotFoundException("Employee with ID : " + e.getId() + " was not found");
        }
    }

    /**
     *
     * @param id employee ID.
     * @return confirmation string
     * @throws EmployeeNotFoundException
     */
    @Override
    public String deleteEmployeeById(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> emp = employeeDao.findById(id);
        if(emp.isPresent()) {
            employeeDao.deleteById(id);
            return "Employee deleted successfully!";
        } else {
            throw new EmployeeNotFoundException("Employee with ID : " + id + " was not found!");
        }
    }

    /**
     *
     * @return confirmation string.
     */
    @Override
    public String deleteAllEmployees() {
        employeeDao.deleteAll();
        return "All Employees deleted successfully!";
    }

    /**
     *
     * @param id employee ID.
     * @return Employee DTO object.
     * @throws EmployeeNotFoundException
     */
    @Override
    public EmployeeDTO getEmployeeWithAddressById(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeDao.findById(id);
        if(employee.isPresent()) {
            Employee e = employee.get();
            EmployeeAddress employeeAddress = employeeAddressService.getEmployeeAddress(id);
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeDetails(e);
            if(null != employeeAddress) {
                employeeDTO.setAddress(employeeAddress.getAddress());
            }
            return employeeDTO;
        } else {
            throw new EmployeeNotFoundException("Employee with ID : " + id + " was not found!");
        }
    }

    /**
     *
     * @return List of employee DTOs
     */
    @Override
    public List<EmployeeDTO> getAllEmployeesWithAddress() {
        List<Employee> employees = employeeDao.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>(employees.size());
        for(Employee emp : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeDetails(emp);
            EmployeeAddress employeeAddress = employeeAddressService.getEmployeeAddress(emp.getId());
            if(null != employeeAddress) {
                employeeDTO.setAddress(employeeAddress.getAddress());
            }
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
}
