package com.lpu.algo;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MySimplePasswordHasher implements PasswordHasher{

	@Override
	public String hash(String password) {
		return password;
	}

	@Override
	public boolean mathc(String hash, String newPassword) {
		return hash==newPassword;
	}

}
