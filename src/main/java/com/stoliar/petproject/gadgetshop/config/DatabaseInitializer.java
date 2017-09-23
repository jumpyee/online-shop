package com.stoliar.petproject.gadgetshop.config;

import com.stoliar.petproject.gadgetshop.entity.User;
import com.stoliar.petproject.gadgetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    private boolean alreadyInitialized = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!alreadyInitialized) {
            initializeAdmin();
            alreadyInitialized = true;
        }
    }

    private void initializeAdmin() {
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("password");
        admin.setRole("admin");
        User user = userService.findUserByEmail(admin.getEmail());
        if (user == null) {
            userService.registerUser(admin);
        }
    }
}
