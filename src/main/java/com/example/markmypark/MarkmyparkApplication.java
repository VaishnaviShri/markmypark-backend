package com.example.markmypark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MarkmyparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkmyparkApplication.class, args);
	}

}
@RestController
class HelloController{
	@GetMapping("/")
	String hello(){
		return "hello vaish";
	}
}
