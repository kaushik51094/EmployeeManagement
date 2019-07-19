package com.target.employeemanagement.exceptions;

public class InvalidEmployeeException extends Exception {
    public InvalidEmployeeException() {
        super("Invalid employee - Please check to ensure the fields firstName, lastName, designation and department " +
                "are specified");
    }

    public InvalidEmployeeException(String message) {
        super(message);
    }
}
