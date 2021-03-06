package com.example.isw.BookManagement.service;

import com.example.isw.BookManagement.model.Member;
import com.example.isw.BookManagement.repository.CategoryRepository;
import com.example.isw.BookManagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationService {
    @Autowired
    private MemberRepository memberRepository;


    public String validateEmail(String email){
        String regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}";
        String message = "";

        if(!email.equals("")) {
            boolean isValid = Pattern.compile(regexp).matcher(email).matches();

            if (!isValid)
                message = "Wrong email format!";
            else
                if(memberRepository.findByEmail(email) != null)
                    message = "Email already in use!";
        }


        return message;
    }


}
