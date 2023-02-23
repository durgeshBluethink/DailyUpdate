package com.blogger.controller;

import com.blogger.entities.Comments;
import com.blogger.payloads.ApiResponse;
import com.blogger.payloads.CommentDto;
import com.blogger.service.CommentsService;
import com.blogger.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentsController {
   @Autowired
    private CommentsService commentsService;
   @Autowired
   private PostService postService;
   @PostMapping("/post/{postId}/comments")
   public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable  Integer postId ){
   CommentDto createComment =  this.commentsService.createComment(commentDto,postId);
   return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
   }
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId")  Integer commentId ){
       this.commentsService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comments Deleted Successfully",true), HttpStatus.CREATED);
    }

}
