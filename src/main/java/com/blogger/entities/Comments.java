package com.blogger.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

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
