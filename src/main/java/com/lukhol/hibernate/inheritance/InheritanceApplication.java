package com.lukhol.hibernate.inheritance;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class InheritanceApplication {

	private final Initializer initializer;

	public static void main(String[] args) {
		SpringApplication.run(InheritanceApplication.class, args);
	}

	@PostConstruct
	public void postConstruct() {
		initializer.prepareDb();
	}
}
