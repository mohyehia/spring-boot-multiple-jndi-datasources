package com.mohyehia.jndi.user.dao;

import com.mohyehia.jndi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
}
