package com.lpu.algo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordAlgoTest {
	
	@Test
	void testEncrypt() {
		PasswordAlgo algo = new PasswordAlgo();
		String input = "thisisPass" ;
		String expected = "ssaPsisiht";
		String actual = algo.encrypt(input);
		assertEquals(expected,actual);
	}

}
