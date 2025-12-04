package com.springbootproject.productslist.service;

import com.springbootproject.productslist.dto.CategoryDTO;
import com.springbootproject.productslist.entity.Category;
import com.springbootproject.productslist.exception.CategoryAlreadyExistsException;
import com.springbootproject.productslist.mapper.CategoryMapper;
import com.springbootproject.productslist.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    // create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Category  "+categoryDTO.getName() +"  Already exists!");
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
    // get all categories
    public List<CategoryDTO>getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper ::toCategoryDTO).toList();
    }
    // get category by id
    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("category not found"));
        return CategoryMapper.toCategoryDTO(category);
    }
    //delete category
    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "Category "+id+ " has been deleted!";
    }
}
