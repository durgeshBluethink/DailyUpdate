package com.blogger.impl;

import com.blogger.entities.Category;
import com.blogger.entities.Post;
import com.blogger.entities.User;
import com.blogger.exception.ResourceNotFoundException;
import com.blogger.payloads.PostDto;
import com.blogger.payloads.PostResponse;
import com.blogger.repo.CategoryRepo;
import com.blogger.repo.PostRepo;
import com.blogger.repo.UserRepo;
import com.blogger.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId ) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        Category category  =this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post addPost = this.postRepo.save(post);

        return this.modelMapper.map(addPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
       Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       post.setImageName(postDto.getImageName());
       Post updatePost = this.postRepo.save(post);

        return this.modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePostById(Integer postId) {
     Post  post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
     this.postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir ) {
        //this is case one for this problem solved
        //this is first Approach
        Sort sort =   (sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        //this is case 2nd  for this problem solved (asc and desc)
        //this is 2nd Approach
//        if(sortDir.equalsIgnoreCase("asc")){
//
//            sort = Sort.by(sortBy).ascending();
//        }
//        else {
//            sort = Sort.by(sortBy).descending();
//        }
      //  Pageable pageable  =  PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending()); // this is sortBy methods uses
        Pageable pageable  =  PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> AllPosts= pagePost.getContent();

        List<PostDto> postDtos = AllPosts.stream().map((post) -> this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse. setContent (postDtos);
        postResponse. setPageNumber (pagePost .getNumber ());
        postResponse. setPageSize (pagePost.getSize());
        postResponse . setTotalElements (pagePost.getTotalElements());
        postResponse. setTotalPages (pagePost.getTotalPages ());
        postResponse.setLastPage(pagePost. isLast ());
        return postResponse;

    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category  = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
         List<PostDto> postDtos= posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class))
                 .collect(Collectors.toList());
        return postDtos;
    }
}
