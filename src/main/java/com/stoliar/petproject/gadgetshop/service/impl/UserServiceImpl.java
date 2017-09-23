package com.stoliar.petproject.gadgetshop.service.impl;

import com.stoliar.petproject.gadgetshop.dao.UserDao;
import com.stoliar.petproject.gadgetshop.entity.Item;
import com.stoliar.petproject.gadgetshop.entity.User;
import com.stoliar.petproject.gadgetshop.service.UserService;
import com.stoliar.petproject.gadgetshop.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final static String AUTHENTICATED_USER_KEY = "authenticatedUser";

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private HttpSession session;

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public boolean registerUser(User user) {
        if (userDao.findUserByEmail(user.getEmail()) != null) {
            return false;
        }
        user.setPassword(securityUtil.hashPassword(user.getPassword()));
        userDao.persist(user);
        return true;
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = userDao.findUserByEmail(email);
        boolean valid = false;
        if (user != null) {
            valid = user.getPassword().equals(securityUtil.hashPassword(password));
            if (valid) {
                session.setAttribute(AUTHENTICATED_USER_KEY, user);
            }
        }
        return valid;
    }

    @Override
    public void logoutUser() {
        session.removeAttribute(AUTHENTICATED_USER_KEY);

    }

    @Override
    public User getAuthenticatedUser() {
        User user = (User) session.getAttribute("authenticatedUser");
        return findUserByEmail(user.getEmail());
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public boolean banUser(String email) {
        User user = userDao.findUserByEmail(email);
        boolean valid = false;
        if (user != null) {
            user.setBanned(true);
            valid = true;
        }
        return valid;
    }

    @Override
    public boolean unbanUser(String email) {
        User user = userDao.findUserByEmail(email);
        boolean valid = false;
        if (user != null) {
            user.setBanned(false);
            valid = true;
        }
        return valid;
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Map<Item, Integer> findCartByUser(String email) {
        return userDao.findCartByUser(userDao.findUserByEmail(email));
    }


}
