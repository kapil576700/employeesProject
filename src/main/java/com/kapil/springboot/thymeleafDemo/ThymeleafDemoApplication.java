package com.kapil.springboot.thymeleafDemo;

import com.kapil.springboot.thymeleafDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class ThymeleafDemoApplication {


	@Bean
	@Scope
	@Qualifier("employee")
	Employee getEmployee(){
		return new Employee();
	}

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafDemoApplication.class, args);
	}


}
