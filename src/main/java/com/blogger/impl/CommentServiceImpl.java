package com.blogger.impl;

import com.blogger.entities.Comments;
import com.blogger.entities.Post;
import com.blogger.exception.ResourceNotFoundException;
import com.blogger.payloads.CommentDto;
import com.blogger.repo.CommentRepo;
import com.blogger.repo.PostRepo;
import com.blogger.service.CommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentsService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException( "Post ", "postId ", postId));
        Comments comment = this.modelMapper.map(commentDto, Comments.class) ;
        comment.setPost (post) ;
        Comments savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comments comments = this.commentRepo.findById (commentId).orElseThrow(() -> new ResourceNotFoundException( "Comments", "commentId ", commentId));
         this.commentRepo.delete(comments);
    }
}
