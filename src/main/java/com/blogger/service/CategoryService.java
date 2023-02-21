package com.blogger.service;

import com.blogger.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //post
    CategoryDto createCategory(CategoryDto categoryDto);
    //put
    CategoryDto updateCategory(CategoryDto categoryDto,Integer CategoryId);
    //delete
    void deleteCategory(Integer CategoryId);
    //get
    CategoryDto getCategoryById(Integer CategoryId);

    //get
   List<CategoryDto> getAllCategories();


}
