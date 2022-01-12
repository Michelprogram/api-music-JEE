package com.example.ApiMusic21;

import com.example.ApiMusic21.model.Artist;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApiMusic21Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiMusic21Application.class, args);
		//System.out.println("Hello**************");
	}

	public void fakeData(){
		//Artist artist = new Artist("xxxtentacion","");
	}

}
