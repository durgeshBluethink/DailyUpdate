package com.blogger.controller;

import com.blogger.payloads.ApiResponse;
import com.blogger.payloads.UserDto;
import com.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("api/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    //POST-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
        UserDto createUserDto  = this.userService.createUser(userDto);
        return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //PUT-Update user
@PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
         UserDto userDto1 = this.userService.updateUser(userDto,userId);
         return ResponseEntity.ok(userDto1);
    }

    //DELETE-Delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public  ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer userId){
       UserDto userDto =  this.userService.deleteUserById(userId);
      // case-1 return ResponseEntity.ok(Map.of("message","User Deleted Successfully"));
    //case -2   return new ResponseEntity<>(Map.of("message","User Deleted Successfully"),HttpStatus.ACCEPTED);
        //case -3
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }

    //GET-Get user
   @GetMapping("/")
    public  ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
     @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
