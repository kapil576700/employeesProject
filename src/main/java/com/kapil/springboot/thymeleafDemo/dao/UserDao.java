package com.kapil.springboot.thymeleafDemo.dao;

import com.kapil.springboot.thymeleafDemo.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User theUser);
}
