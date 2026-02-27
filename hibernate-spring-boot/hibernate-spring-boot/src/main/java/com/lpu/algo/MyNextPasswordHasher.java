package com.lpu.algo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class MyNextPasswordHasher implements PasswordHasher{

	@Override
	public String hash(String password) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<password.length();i++) {
			char c = password.charAt(i);
				sb.append( (char) ( (int)c + 1 ) );
			}
		return sb.toString();
		}

	@Override
	public boolean mathc(String hash, String newPassword) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<newPassword.length();i++) {
			char c = newPassword.charAt(i);
			if(c=='z') sb.append('a');
			else {
				sb.append( (char)((int)c + 1) );
			}
		}
		return sb.toString().equals(hash);
	}

}
