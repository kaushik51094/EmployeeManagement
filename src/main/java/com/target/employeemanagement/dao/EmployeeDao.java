package com.target.employeemanagement.dao;

import com.target.employeemanagement.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends MongoRepository<Employee, String> {

}
