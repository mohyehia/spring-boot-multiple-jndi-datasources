package com.mohyehia.jndi.user.service.implementation;

import com.mohyehia.jndi.user.dao.UserDAO;
import com.mohyehia.jndi.user.entity.User;
import com.mohyehia.jndi.user.service.framework.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findById(long id) {
        return userDAO.findById(id).orElse(null);
    }
}
