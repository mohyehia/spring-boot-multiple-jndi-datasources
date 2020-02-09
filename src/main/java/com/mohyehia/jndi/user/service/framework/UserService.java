package com.mohyehia.jndi.user.service.framework;

import com.mohyehia.jndi.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User save(User user);
    User findById(long id);
}
