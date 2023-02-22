package com.blogger.service;

import com.blogger.entities.Post;
import com.blogger.payloads.PostDto;
import com.blogger.payloads.PostResponse;

import java.util.List;

public interface PostService{
    //post
    PostDto createPost(PostDto postDto, Integer categoryId,Integer userId);
    //put
    PostDto updatePost(PostDto postDto,Integer postId);
    //delete
    void  deletePostById(Integer postId);
    //getby id
    PostDto getPostById(Integer postId);
    //get all post
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get all post by category's

    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user's
    List<PostDto> getPostByUser(Integer userId);

    //search posts
    List<PostDto>searchPosts(String keyword);

}
