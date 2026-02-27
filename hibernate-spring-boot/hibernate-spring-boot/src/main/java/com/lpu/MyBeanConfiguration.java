package com.lpu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.lpu.algo.MyNextPasswordHasher;
import com.lpu.algo.MySimplePasswordHasher;
import com.lpu.algo.PasswordHasher;



@Configuration
@ImportResource("classpath:beans.xml")
public class MyBeanConfiguration {
	
	@Bean
//	@Primary
	PasswordHasher simplePasswordHasher() {
		return new MySimplePasswordHasher();
	}
	
	@Bean
	PasswordHasher nextPasswordHasher() {
		return new MyNextPasswordHasher();
	}
}
