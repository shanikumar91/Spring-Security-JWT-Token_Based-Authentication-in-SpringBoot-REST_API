package com.springbootproject.productslist.controller;

import com.springbootproject.productslist.dto.CategoryDTO;
import com.springbootproject.productslist.exception.CategoryAlreadyExistsException;
import com.springbootproject.productslist.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;
@Tag(
        name = "Category REST API CRUD Operation",
        description = "CREATE, READ, UPDATE and DELETE operation for Category REST API "
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    //get all categories
    @Operation(
            summary = "Fetch all categories",
            description = "REST API to Fetch all Categories"
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //create categories
    @Operation(
            summary = "create category",
            description = "REST API to create category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // get category by id
    @Operation(
            summary = "Fetch category by id",
            description = "REST API to fetch category by id"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //delete category
    @Operation(
            summary = "Delete category by id",
            description = "REST API to delete category by id"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
       return categoryService.deleteCategory(id);

    }
}
