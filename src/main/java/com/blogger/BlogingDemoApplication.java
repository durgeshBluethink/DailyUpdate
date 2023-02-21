package com.blogger;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogingDemoApplication.class, args);
		System.out.println("run...");
	}
   @Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
