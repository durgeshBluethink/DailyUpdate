package com.blogger.service.impl;

import com.blogger.payloads.CategoryDto;
import com.blogger.repo.CategoryRepo;
import com.blogger.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImp implements CategoryService {
     @Autowired
    private CategoryRepo categoryRepo;
     @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId) {
        return null;
    }

    @Override
    public void deleteCategory(Integer CategoryId) {

    }

    @Override
    public CategoryDto getCategoryById(Integer CategoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return null;
    }
}
