package com.example.isw.BookManagement.repository;

import com.example.isw.BookManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findAllByOrderByFullNameAsc();
    public List<User> findAllByActiveOrderByFullNameAsc(Integer active);
    public User findByUsername(String username);
}
