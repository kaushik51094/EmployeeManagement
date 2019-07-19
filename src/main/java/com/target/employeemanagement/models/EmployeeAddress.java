package com.target.employeemanagement.models;

import org.springframework.stereotype.Component;

@Component
public class EmployeeAddress {

    private String address;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(null != address) {
            this.address = address.trim();
        }
    }

    public boolean isValidEmployeeAddress() {
        return null != address && !address.equals("");
    }
}
