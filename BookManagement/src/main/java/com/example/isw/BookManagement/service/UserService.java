package com.example.isw.BookManagement.service;

import com.example.isw.BookManagement.model.User;
import com.example.isw.BookManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAll(){
        return userRepository.findAllByOrderByFullNameAsc();
    }

    public List<User> getAllActiveUsers(){
        return userRepository.findAllByActiveOrderByFullNameAsc(1);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getById(Integer id){
        return userRepository.findById(id).get();
    }

    public User addNewUser(User user){
        user.setActive(1);
        user.setLastModifiedDate(user.getCreatedDate());
        user.setCreatedDate(user.getCreatedDate());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user){
        user.setLastModifiedDate(new Date());
        return userRepository.save(user);
    }

    public void deteleUser(User user){
        userRepository.delete(user);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
}
