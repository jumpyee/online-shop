package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.entity.User;

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
