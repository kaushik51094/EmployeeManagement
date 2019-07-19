package com.target.employeemanagement.controllers;

import com.target.employeemanagement.models.Employee;
import com.target.employeemanagement.services.impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeServiceImpl employeeService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAddEmployee() throws Exception {
        Employee emp = new Employee();
        try {
            when(employeeService.addEmployee(any(Employee.class))).thenReturn(emp);
        } catch(Exception ex) {

        }
        mvc.perform(get("/employee/add")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
