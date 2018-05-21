package org.chronotics.pithos;

import org.chronotics.db.mybatis.app.service.ServiceUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
		"org.chronotics.pithos",
		"org.chronotics.db.mybatis",
		"org.chronotics.db.mybatis.app.controller",
		"org.chronotics.db.mybatis.app.mapper",
		"org.chronotics.db.mybatis.app.service"})
@SpringBootApplication
public class Application { //extends RepositoryRestMvcConfiguration {
	
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class,args);

		ServiceUser service = (ServiceUser)context.getBean("serviceUser");
		service.selectListByName("testUser");
	}

}
