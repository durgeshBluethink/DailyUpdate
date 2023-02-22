package com.blogger.controller;

import com.blogger.entities.Post;
import com.blogger.payloads.ApiResponse;
import com.blogger.payloads.PostDto;
import com.blogger.payloads.PostResponse;
import com.blogger.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;

    //create
     @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
             )
     {
         PostDto  createPost=  this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }
    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser (@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser (@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    //put
     @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
       PostDto updatePost =   this.postService.updatePost(postDto,postId);
         return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/posts/{postId}")
     public ApiResponse deletePost(@PathVariable Integer postId){
         this.postService.deletePostById(postId);
         return new ApiResponse("Post Deleted Successfully",true);
     }

    //get
    @GetMapping("/posts/{postId}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
         return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }
    //get all


//    @GetMapping("/posts")
//    public ResponseEntity<List<PostDto>> getAllPosts(){
//        List<PostDto> allPost =  this.postService.getAllPosts();
//        return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
//    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost (
            @RequestParam (value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
      PostResponse postResponse =   this.postService.getAllPosts(pageNumber, pageSize);
       // System.out.println(allPost);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }
}
