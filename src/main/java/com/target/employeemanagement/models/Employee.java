package com.target.employeemanagement.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
public class Employee {
    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private String designation;

    private String department;

    private int salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(null != firstName) {
            this.firstName = firstName.trim();
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(null != lastName) {
            this.lastName = lastName.trim();
        }
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if(null != designation) {
            this.designation = designation.trim();
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if(null != department) {
            this.department = department.trim();
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        StringBuilder employee = new StringBuilder("Employee - \n");
        employee.append("Id: " + this.id + "\n")
        .append("First Name: " + this.firstName + "\n")
        .append("Last Name: " + this.lastName + "\n")
        .append("Designation: " + this.designation + "\n")
        .append("Department: " + this.department + "\n")
        .append("Salary: " + this.salary + "\n");
        return employee.toString();
    }

    public boolean isEmployeeValid() {
        return null != this.firstName && !this.firstName.equals("") && null != this.lastName &&
                !this.lastName.equals("") && null != this.department && !this.department.equals("") &&
                null != this.designation && !this.designation.equals("");
    }
}
