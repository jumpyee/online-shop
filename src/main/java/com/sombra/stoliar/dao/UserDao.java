package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.User;

import java.util.List;

public interface UserDao {
    User persist(User user);

    void delete(User user);

    User findUserByEmail(String email);

    List<User> findAllUsers();
}
