package com.blogger.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @ManyToOne
    private Post post;
//    @ManyToOne
//    private  User user ;
}
