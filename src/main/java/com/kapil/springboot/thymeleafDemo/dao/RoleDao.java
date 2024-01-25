package com.kapil.springboot.thymeleafDemo.dao;

import com.kapil.springboot.thymeleafDemo.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}