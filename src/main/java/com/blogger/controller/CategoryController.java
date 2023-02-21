package com.blogger.controller;

import com.blogger.entities.Category;
import com.blogger.payloads.ApiResponse;
import com.blogger.payloads.CategoryDto;
import com.blogger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

     //Create
     @PostMapping("/")
      public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
          CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
          return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
      }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId ){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId ){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category Deleted Successfully",true), HttpStatus.OK);
    }
    //Get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer categoryId ){
   CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
   return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
    //Get All

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory( ){
      List<CategoryDto> categoryDtos = this.categoryService.getAllCategories();
      return ResponseEntity.ok(categoryDtos);
    }
}
