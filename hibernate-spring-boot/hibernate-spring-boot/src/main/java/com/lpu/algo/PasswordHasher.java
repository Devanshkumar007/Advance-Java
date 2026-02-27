package com.lpu.algo;

public interface PasswordHasher {

	public String hash(String password);
	public boolean mathc(String hash, String newPassword);
}
