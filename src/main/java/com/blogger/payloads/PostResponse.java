package com.blogger.payloads;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
 @NoArgsConstructor
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private  long totalElements;
    private  int totalPages;
    private boolean lastPage;
    private  String sortBy;
    private String sortDir;
}
