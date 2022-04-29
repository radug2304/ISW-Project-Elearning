package com.example.isw.BookManagement;


import com.example.isw.BookManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SuccessLoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService service;

    @Autowired
    private HttpSession httpSession;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authEvent){
        User user = (User) authEvent.getAuthentication().getPrincipal();
        String fullName = service.getByUsername(user.getUsername()).getFullName();
        httpSession.setAttribute("userLoggedIn",fullName);
    }
}
