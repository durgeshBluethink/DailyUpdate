package com.blogger.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Post {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String title;
    private String content;
    private
    String imageName;
    private Date addedDate;
    @ManyToOne
    private  Category category;
    @ManyToOne
    private User user;
}
