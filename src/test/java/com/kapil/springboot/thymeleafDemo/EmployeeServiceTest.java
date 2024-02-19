package com.kapil.springboot.thymeleafDemo;


import com.kapil.springboot.thymeleafDemo.dao.EmployeeRepository;
import com.kapil.springboot.thymeleafDemo.entity.Employee;
import com.kapil.springboot.thymeleafDemo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {

    private Employee employee;

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    public void beforeEach(){
        employee = new Employee();
        employee.setFirstName("Kapil");
        employee.setLastName("Singh");
        employee.setEmail("kap@xyz.com");
        employee.setId(1);
    }
    @Test
    public void employeeFindByIdTest(){


        when(employeeRepository.findById(1)).thenReturn(Optional.ofNullable(employee));

        assertEquals(employee,employeeService.findById(1),"should be same");

        verify(employeeRepository, times(1)).findById(1);
    }
}
