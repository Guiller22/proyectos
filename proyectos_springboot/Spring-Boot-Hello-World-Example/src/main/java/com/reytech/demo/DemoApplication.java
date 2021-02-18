package com.reytech.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Es necesario una clase main como punto de inicio de la aplicación. 
//Esta es com.reytech.demo.
//La anotación @SpringBootApplication indica en donde inicia la aplicación.

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
