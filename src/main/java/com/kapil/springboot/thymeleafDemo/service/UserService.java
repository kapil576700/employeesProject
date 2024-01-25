package com.kapil.springboot.thymeleafDemo.service;

import com.kapil.springboot.thymeleafDemo.entity.User;
import com.kapil.springboot.thymeleafDemo.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);

    void save(WebUser webUser);

}
