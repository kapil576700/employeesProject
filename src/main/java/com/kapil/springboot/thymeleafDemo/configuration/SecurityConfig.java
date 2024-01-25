package com.kapil.springboot.thymeleafDemo.configuration;

import com.kapil.springboot.thymeleafDemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/employees").hasRole("EMPLOYEE")
                        .requestMatchers("/employees/addForm").hasRole("MANAGER")
                        .requestMatchers("/employees/updateForm").hasRole("MANAGER")
                        .requestMatchers("/employees/delete").hasRole("ADMIN")
                        .requestMatchers("/register/**").permitAll()


                        .anyRequest().authenticated()
        )
                .formLogin(form ->
                        form
                                .loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateUser")
                                .successHandler(customAuthenticationSuccessHandler)
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                );
        return http.build();
    }
}

/*@Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails tina = User.builder()
                .username("tina")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, tina);
    }
     */

