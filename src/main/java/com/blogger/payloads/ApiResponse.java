package com.blogger.payloads;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private  String massage;
    private boolean succsess;
}
