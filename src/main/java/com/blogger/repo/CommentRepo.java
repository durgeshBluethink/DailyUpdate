package com.blogger.repo;

import com.blogger.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
}
