package com.target.employeemanagement.Service;

import com.target.employeemanagement.dao.EmployeeDao;
import com.target.employeemanagement.exceptions.EmployeeNotFoundException;
import com.target.employeemanagement.exceptions.InvalidEmployeeAddressException;
import com.target.employeemanagement.exceptions.InvalidEmployeeException;
import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.models.EmployeeAddress;
import com.target.employeemanagement.models.EmployeeDTO;
import com.target.employeemanagement.services.EmployeeAddressService;
import com.target.employeemanagement.services.EmployeeService;
import com.target.employeemanagement.services.impl.EmployeeAddressServiceImpl;
import com.target.employeemanagement.services.impl.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Mock
    private EmployeeAddressService employeeAddressService = new EmployeeAddressServiceImpl();

    @Mock
    private EmployeeDao employeeDao;

    @Test
    public void testEmployeeServiceExists() {
        Assert.assertNotNull(employeeService);
    }

    @Test
    public void addEmployee_addsEmployeeIfValid() throws InvalidEmployeeException {
        Employee mockEmployee = new Employee(1, "test", "user", "Engineer", "Software", 100000);
        when(employeeDao.insert(any(Employee.class))).thenReturn(mockEmployee);
        Employee emp = employeeService.addEmployee(mockEmployee);
        Assert.assertNotNull(emp);
        Assert.assertEquals(emp.getId(), mockEmployee.getId());
        Assert.assertEquals(emp.getFirstName(), mockEmployee.getFirstName());
        Assert.assertEquals(emp.getLastName(), mockEmployee.getLastName());
        Assert.assertEquals(emp.getDesignation(), mockEmployee.getDesignation());
        Assert.assertEquals(emp.getDepartment(), mockEmployee.getDepartment());
        Assert.assertEquals(emp.getSalary(), mockEmployee.getSalary());
    }

    @Test(expected = InvalidEmployeeException.class)
    public void addEmployee_throwsInvalidEmployeeException_ifEmployeeIsInvalid() throws InvalidEmployeeException {
        Employee mockEmployee = new Employee(1, null, "user", "Engineer", "Software", 100000);
        employeeService.addEmployee(mockEmployee);
    }

    @Test
    public void addEmployees_addsEmployeesIfValid() throws InvalidEmployeeException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        Employee e2 = new Employee(2, "test2", "user2", "Engineer2", "Software2", 100000);
        Employee e3 = new Employee(3, "test3", "user3", "Engineer3", "Software3", 110000);
        List<Employee> mockEmployees = new ArrayList<>(3);
        mockEmployees.add(e1);
        mockEmployees.add(e2);
        mockEmployees.add(e3);
        when(employeeDao.insert(any(List.class))).thenReturn(mockEmployees);
        List<Employee> employees = employeeService.addEmployees(mockEmployees);
        Assert.assertEquals(employees.get(0), mockEmployees.get(0));
        Assert.assertEquals(employees.get(1), mockEmployees.get(1));
        Assert.assertEquals(employees.get(2), mockEmployees.get(2));
    }

    @Test(expected = InvalidEmployeeException.class)
    public void addEmployees_throwsInvalidEmployeeException_ifAtleastOneEmployeeIsInvalid() throws InvalidEmployeeException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        Employee e2 = new Employee(2, "test2", "", "Engineer2", "Software2", 100000);
        Employee e3 = new Employee(3, "test3", "user3", "Engineer3", "Software3", 110000);
        List<Employee> mockEmployees = Arrays.asList(e1, e2, e3);
        employeeService.addEmployees(mockEmployees);
    }

    @Test
    public void getEmployeeById_returnsEmployeeIfFound() {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        when(employeeDao.findById(any(Integer.class))).thenReturn(java.util.Optional.of(e1));
        Optional<Employee> emp = employeeService.getEmployeeById(1);
        Assert.assertTrue(emp.isPresent());
    }

    @Test
    public void getEmployeeById_returnsEmptyIfEmployeeNotFound() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Optional<Employee> emp = employeeService.getEmployeeById(1);
        Assert.assertFalse(emp.isPresent());
    }

    @Test
    public void getAllEmployees_returnsEmployees() {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        Employee e2 = new Employee(2, "test2", "user2", "Engineer2", "Software2", 100000);
        Employee e3 = new Employee(3, "test3", "user3", "Engineer3", "Software3", 110000);
        List<Employee> mockEmployees = Arrays.asList(e1, e2, e3);
        when(employeeDao.findAll()).thenReturn(mockEmployees);
        List<Employee> employees = employeeService.getAllEmployees();
        Assert.assertEquals(employees.size(), 3);
    }

    @Test
    public void updateEmployee_updatesEmployeeIfPresentAndValid() throws InvalidEmployeeException, EmployeeNotFoundException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        Employee e2 = new Employee(1, "test2", "user2", "Engineer2", "Software2", 90000);
        when(employeeDao.findById(1)).thenReturn(Optional.of(e1));
        when(employeeDao.save(any(Employee.class))).thenReturn(e2);
        Employee e = employeeService.updateEmployee(e2);
        Assert.assertEquals(e, e2);
    }

    @Test(expected = InvalidEmployeeException.class)
    public void updateEmployee_throwsInvalidEmployeeException_ifEmployeeIsInvalid() throws InvalidEmployeeException, EmployeeNotFoundException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        Employee e2 = new Employee(1, "", "user1", "Engineer1", "Software1", 90000);
        when(employeeDao.findById(1)).thenReturn(Optional.of(e1));
        employeeService.updateEmployee(e2);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void updateEmployee_throwsEmployeeNotFoundException_ifEmployeeIsUnavailable() throws InvalidEmployeeException, EmployeeNotFoundException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        when(employeeDao.findById(1)).thenReturn(Optional.empty());
        employeeService.updateEmployee(e1);
    }

    @Test
    public void deleteEmployeeById_deletesEmployeeIfFound() throws EmployeeNotFoundException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        when(employeeDao.findById(1)).thenReturn(Optional.of(e1));
        String result = employeeService.deleteEmployeeById(1);
        Assert.assertEquals(result, "Employee deleted successfully!");
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void deleteEmployeeById_throwsEmployeeNotFoundException_ifEmployeeIsNotFound() throws EmployeeNotFoundException {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        employeeService.deleteEmployeeById(1);
    }

    @Test
    public void deleteAllEmployees() {
        String result = employeeService.deleteAllEmployees();
        Assert.assertEquals(result, "All Employees deleted successfully!");
    }

    @Test
    public void testGetEmployeeWithAddressById_returnsEmployeeWithAddressIfAvailable() throws EmployeeNotFoundException, InvalidEmployeeAddressException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software", 90000);
        EmployeeAddress employeeAddress = new EmployeeAddress();
        employeeAddress.setId(1);
        employeeAddress.setAddress("Bangalore, India");
        when(employeeDao.findById(1)).thenReturn(Optional.of(e1));
        when(employeeAddressService.getEmployeeAddress(1)).thenReturn(employeeAddress);
        EmployeeDTO employee = employeeService.getEmployeeWithAddressById(1);
        Assert.assertTrue(employee.getId() == 1);
        Assert.assertEquals(employee.getFirstName(), "test1");
        Assert.assertEquals(employee.getLastName(), "user1");
        Assert.assertEquals(employee.getDesignation(), "Engineer1");
        Assert.assertEquals(employee.getDepartment(), "Software");
        Assert.assertEquals(employee.getSalary(), 90000);
        Assert.assertEquals(employee.getAddress(), "Bangalore, India");
    }

    @Test
    public void testGetEmployeeWithAddressById_returnsEmployeeWithoutAddressIfUnavailable() throws EmployeeNotFoundException, InvalidEmployeeAddressException {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software", 90000);
        when(employeeDao.findById(1)).thenReturn(Optional.of(e1));
        when(employeeAddressService.getEmployeeAddress(1)).thenReturn(null);
        EmployeeDTO employee = employeeService.getEmployeeWithAddressById(1);
        Assert.assertTrue(employee.getId() == 1);
        Assert.assertEquals(employee.getFirstName(), "test1");
        Assert.assertEquals(employee.getLastName(), "user1");
        Assert.assertEquals(employee.getDesignation(), "Engineer1");
        Assert.assertEquals(employee.getDepartment(), "Software");
        Assert.assertEquals(employee.getSalary(), 90000);
        Assert.assertNull(employee.getAddress());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testGetEmployeeWithAddressById_throwsEmployeeNotFoundException() throws EmployeeNotFoundException {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        employeeService.getEmployeeWithAddressById(1);
    }

    @Test
    public void testGetAllEmployeesWithAddress_returnsEmployees() {
        Employee e1 = new Employee(1, "test1", "user1", "Engineer1", "Software1", 90000);
        Employee e2 = new Employee(2, "test2", "user1", "Engineer1", "Software1", 90000);
        Employee e3 = new Employee(3, "test3", "user3", "Engineer3", "Software3", 90000);
        List<Employee> employees = Arrays.asList(e1, e2, e3);
        when(employeeDao.findAll()).thenReturn(employees);
        EmployeeAddress ea1 = new EmployeeAddress(1, "Bangalore, India");
        EmployeeAddress ea2 = new EmployeeAddress(1, "Coimbatore, India");
        when(employeeAddressService.getEmployeeAddress(1)).thenReturn(ea1);
        when(employeeAddressService.getEmployeeAddress(2)).thenReturn(ea2);
        List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployeesWithAddress();
        Assert.assertEquals(employeeDTOS.get(0).getAddress(), "Bangalore, India");
        Assert.assertEquals(employeeDTOS.get(1).getAddress(), "Coimbatore, India");
        Assert.assertNull(employeeDTOS.get(2).getAddress());
    }
}
