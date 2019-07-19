package com.target.employeemanagement.exceptions;

public class InvalidEmployeeAddressException extends Exception {
    public InvalidEmployeeAddressException() {
        super("Invalid employee address!");
    }

    public InvalidEmployeeAddressException(String message) {
        super(message);
    }
}
