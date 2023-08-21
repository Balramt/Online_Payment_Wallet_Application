package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootMain {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootMain.class, args);
		String beans[]= context.getBeanDefinitionNames();
		/*for(String bean: beans){
			System.out.println(bean);
		}*/
		

	}
}
