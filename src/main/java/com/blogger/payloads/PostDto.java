package com.blogger.payloads;

import com.blogger.entities.Category;
import com.blogger.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@NoArgsConstructor
public class PostDto {
   private Integer postId;
    private String title;
    private String content;
    private
    String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;

}
