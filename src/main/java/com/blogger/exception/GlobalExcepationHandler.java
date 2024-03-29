package com.blogger.exception;

import com.blogger.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExcepationHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message  = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
  @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex, HttpStatus status){
   Map<String, String> errors = new HashMap<>();
   ex.getBindingResult().getAllErrors().forEach((error)->{
       String fieldName = ((FieldError)error).getField();
       String message = error.getDefaultMessage();
       errors.put(fieldName,message);
   });
  //return ResponseEntity.ok(errors);
      return new ResponseEntity<Map<String, String>>(errors,HttpStatus.BAD_REQUEST);
    }
}
