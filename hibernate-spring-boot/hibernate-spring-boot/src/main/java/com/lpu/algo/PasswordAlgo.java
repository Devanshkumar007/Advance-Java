package com.lpu.algo;

import org.springframework.stereotype.Component;

@Component
public class PasswordAlgo {
	public String encrypt (String password) {
		StringBuilder sb= new StringBuilder(password);
		return sb.reverse().toString();
	}
}
