package com.nishant.QuizDemo;

import java.util.TimeZone;
import javax.annotation.PostConstruct;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackageClasses = { 
		QuizDemoApplication.class,
		Jsr310JpaConverters.class 
})
public class QuizDemoApplication {
	
//	@PostConstruct
//	void init() {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(QuizDemoApplication.class, args);
	}

}

