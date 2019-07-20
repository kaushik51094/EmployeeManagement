Employee Management -

Spring boot application integrated with mongo atlas and apache kafka running on local.

Pre-requisites -
1. Maven
2. Java 8
3. Sonar (for coverage and static code analysis)

To run the application -
1. Start Zookeeper
2. Start Kafka server
3. Navigate to the employee-management directory
4. Execute
```
mvn spring-boot:run
```
5. The application runs on localhost:8080



To run tests in the application with jacoco -
1. Navigate to the employee-management directory
2. Execute
```
mvn clean test
```



To run sonar -
1. Start local sonar server
2. Navigate to the employee-management directory
3. Execute
```
mvn sonar:sonar
```




JSON structure -
Employee object -
```
{id: Integer,
firstName: String,
lastName: String,
designation: String,
department: String,
salary: int}
```

EmployeeAddress object -
```
{id: Integer,
address: String}
```





REST APIs -
Include Basic Authorization header with username : user and password : 123456 (defined in application.properties)
1. Add Employee to mongo -
POST /employee
Request body - Employee JSON

2. Get Employee by ID from mongo -
GET /employee/{ID}

3. Update Employee in mongo -
PUT /employee
Request body - Employee JSON

4. Delete Employee from mongo -
DELETE /employee/{ID}

5. Add multiple employees to mongo -
POST /employees
Request body - Array of Employee JSON

6. Get all employees from mongo -
GET /employees

7. Delete all employees from mongo -
DELETE /employees

8. Add employee address to Kafka -
POST /employeeAddress
Request body - EmployeeAddress JSON

9. Get employee address from Kafka -
GET /employeeAddress/{ID}

10. Get employee with address from mongo and Kafka -
GET /employee/withAddress/{ID}

11. Get all employees with address from mongo and Kafka -
GET /employees/withAddress



Configurations -
Defined in application.properties for mongo, kafka and spring security