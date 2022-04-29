package com.example.isw.BookManagement.service;

import com.example.isw.BookManagement.model.Category;
import com.example.isw.BookManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Long getCountTotal(){
        return categoryRepository.count();
    }

    public List<Category> getAllSorted(){
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id){
        return categoryRepository.getById(id);
    }

    public Category addNewCategory(Category category){
        category.setCreateDate(new Date());
        return categoryRepository.save(category);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public boolean hasUsage(Category category){
        return category.getBooks().size() > 0;
    }

    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }

    public void deleteCategoryById(Integer id){
        categoryRepository.deleteById(id);
    }


}
