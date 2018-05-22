package org.chronotics.pithos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
		"org.chronotics.pithos",
		"org.chronotics.db.mybatis"})
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				SpringApplication.run(Application.class,args);

	}

}
