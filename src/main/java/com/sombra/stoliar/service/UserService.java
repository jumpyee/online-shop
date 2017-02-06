package com.sombra.stoliar.service;

import com.sombra.stoliar.entity.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    boolean registerUser(User user);

    boolean loginUser(String email, String password);

    void logoutUser();

    List<User> findAllUsers();

    boolean banUser(String email);

    boolean unbanUser(String email);
}
