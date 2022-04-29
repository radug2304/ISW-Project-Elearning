package com.example.isw.BookManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class HomeService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    public Map<String, Long> getTotalCounts(){
        Map<String, Long> countMap = new HashMap<>();
        countMap.put("totalStudents",memberService.getCountStudents());
        countMap.put("totalMembers",memberService.getCountTotal());
        countMap.put("totalParents",memberService.getCountParents());
        countMap.put("totalBooks",bookService.getCountTotal());
        countMap.put("totalCategories",categoryService.getCountTotal());
        countMap.put("totalIssuedBooks",bookService.getIssuedBooksTotal());
        return countMap;
    }
}
