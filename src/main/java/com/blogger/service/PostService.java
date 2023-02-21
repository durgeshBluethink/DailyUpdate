package com.blogger.service;

import com.blogger.entities.Post;
import com.blogger.payloads.PostDto;

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
    List<PostDto> getAllPosts();

    //get all post by category's

    List<Post> getPostByCategory(Integer categoryId);

    //get all post by user's
    List<Post> getPostByUser(Integer userId);

    //search posts
    List<Post>searchPosts(String keyword);

}
