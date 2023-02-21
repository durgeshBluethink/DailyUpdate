package com.blogger.payloads;

import com.blogger.entities.Category;
import com.blogger.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Date;
@Data
@NoArgsConstructor
public class PostDto {

    private String title;
    private String content;
    private
    String imageName;
    private Date addedDate;
    private Category category;
    private User user;

}
