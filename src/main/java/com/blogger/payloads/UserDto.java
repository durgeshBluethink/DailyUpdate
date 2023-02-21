package com.blogger.payloads;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters")
    private  String name;
    @Email(message = "Email address is not valid !!")
    private  String email;
    @NotEmpty
    @Size (min = 3, max = 10, message =" Password must be min of 3 chars and max of 10 chars !!")
    private String password;
    @NotEmpty
    private String about;

}
