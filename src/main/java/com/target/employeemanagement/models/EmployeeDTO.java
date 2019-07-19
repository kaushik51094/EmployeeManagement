package com.target.employeemanagement.models;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeDTO extends Employee{
    private String address;

//    public EmployeeDTO(Employee employee, EmployeeAddress employeeAddress) {
//        this.setId(employee.getId());
//        this.setFirstName(employee.getFirstName());
//        this.setLastName(employee.getLastName());
//        this.setDesignation(employee.getDesignation());
//        this.setDepartment(employee.getDepartment());
//        this.setSalary(employee.getSalary());
//        this.setAddress(employeeAddress.getAddress());
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmployeeDetails(Employee employee) {
        this.setId(employee.getId());
        this.setFirstName(employee.getFirstName());
        this.setLastName(employee.getLastName());
        this.setDesignation(employee.getDesignation());
        this.setDepartment(employee.getDepartment());
        this.setSalary(employee.getSalary());
    }
}
