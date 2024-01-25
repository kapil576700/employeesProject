package com.kapil.springboot.thymeleafDemo.controller;

import com.kapil.springboot.thymeleafDemo.entity.Employee;
import com.kapil.springboot.thymeleafDemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController (EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }

    @GetMapping("/list")
    public String employeesList(Model model){
        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "employees/employee-list";
    }

    @GetMapping("/addForm")
    public String addform(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee theEmployee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "employees/employee-form";
        }else{
            employeeService.save(theEmployee);
            return "redirect:/employees/list";
        }

    }

    @GetMapping("/updateForm")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
