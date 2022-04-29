package com.example.isw.BookManagement;

import com.example.isw.BookManagement.common.Constants;
import com.example.isw.BookManagement.model.User;
import com.example.isw.BookManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent readyEvent){
        initDatabase();
    }

    private void initDatabase(){
        if(userService.getAll().size() == 0)
            userService.addNewUser(new User("Admin","admin","admin", Constants.ADMIN));

    }
}
