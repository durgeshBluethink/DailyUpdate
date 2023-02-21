package com.blogger.impl;

import com.blogger.entities.Category;
import com.blogger.exception.ResourceNotFoundException;
import com.blogger.payloads.CategoryDto;
import com.blogger.repo.CategoryRepo;
import com.blogger.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryImp implements CategoryService {
     @Autowired
    private CategoryRepo categoryRepo;
     @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category  = this.modelMapper.map(categoryDto,Category.class);
        Category addCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(addCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        //getOld Category
       Category category =  this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
       //update old category
       category.setCategoryTitle(categoryDto.getCategoryTitle());
       category.setCategoryDescription(categoryDto.getCategoryDescription());

       //save new category
       Category updateCategory = this.categoryRepo.save(category);
       // return category
        return this.modelMapper.map(updateCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
      Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
       this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));

        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto>categoryDtos = categories.stream().map((category) -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
