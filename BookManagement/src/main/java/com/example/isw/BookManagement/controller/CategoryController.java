package com.example.isw.BookManagement.controller;

import com.example.isw.BookManagement.model.Category;
import com.example.isw.BookManagement.repository.CategoryRepository;
import com.example.isw.BookManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String showCategoriesPage(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "/category/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCategoryPage(Model model) {
		model.addAttribute("category", new Category());
		return "/category/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editCategoryPage(@PathVariable(name = "id") Integer id, Model model) {
		Category category = categoryService.getCategoryById(id);
		if( category != null ) {
			model.addAttribute("category", category);
			return "/category/form";
		} else {
			return "redirect:/category/add";
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCategory(@Valid Category category, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

		if(categoryRepository.findByName(category.getName()) != null)
		{
			bindingResult.rejectValue("name","name","Name already exists!");
			return "/category/form";
		}

		if(categoryRepository.findByShortName(category.getShortName()) != null)
		{
			bindingResult.rejectValue("shortName","shortName","Short Name already exists!");
			return "/category/form";
		}
		if( bindingResult.hasErrors() ) {
			return "/category/form";
		}
		
		if( category.getId() == null ) {
			categoryService.addNewCategory(category);
			redirectAttributes.addFlashAttribute("successMsg", "'" + category.getName() + "' is added as a new category.");
			return "redirect:/category/add";
		} else {
			Category updateCategory = categoryService.saveCategory( category );
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + category.getName() + "' are saved successfully. ");
			return "redirect:/category/edit/"+updateCategory.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeCategory(@PathVariable(name = "id") Integer id, Model model) {
		Category category = categoryService.getCategoryById( id );
		if( category != null ) {
			if( categoryService.hasUsage(category) ) {
				model.addAttribute("categoryInUse", true);
				return showCategoriesPage(model);
			} else {
				categoryService.deleteCategoryById(id);
			}
		}
		return "redirect:/category/list";
	}
	
}
