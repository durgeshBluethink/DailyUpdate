package com.blogger.service;

import com.blogger.payloads.CommentDto;

public interface CommentsService {
    CommentDto createComment (CommentDto commentDto, Integer postId);
    void deleteComment (Integer commentId) ;
}
