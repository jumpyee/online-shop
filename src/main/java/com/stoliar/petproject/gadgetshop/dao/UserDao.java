package com.stoliar.petproject.gadgetshop.dao;

import com.stoliar.petproject.gadgetshop.entity.Item;
import com.stoliar.petproject.gadgetshop.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User persist(User user);

    void delete(User user);

    User findUserByEmail(String email);

    List<User> findAllUsers();

    User updateUser(User user);


    Map<Item, Integer> findCartByUser(User user);


}
