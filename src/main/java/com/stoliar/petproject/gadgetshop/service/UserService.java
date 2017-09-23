package com.stoliar.petproject.gadgetshop.service;

import com.stoliar.petproject.gadgetshop.entity.Item;
import com.stoliar.petproject.gadgetshop.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User findUserByEmail(String email);

    boolean registerUser(User user);

    boolean loginUser(String email, String password);

    void logoutUser();

    User getAuthenticatedUser();

    List<User> findAllUsers();

    boolean banUser(String email);

    boolean unbanUser(String email);

    User updateUser(User user);

    Map<Item, Integer> findCartByUser(String email);
}
