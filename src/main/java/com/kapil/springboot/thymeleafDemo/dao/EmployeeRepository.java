package com.kapil.springboot.thymeleafDemo.dao;

import com.kapil.springboot.thymeleafDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

public List<Employee> findAllByOrderByLastNameAsc();
}
