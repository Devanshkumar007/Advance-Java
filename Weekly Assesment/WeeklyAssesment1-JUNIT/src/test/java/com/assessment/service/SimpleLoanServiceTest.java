package com.assessment.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleLoanServiceTest {
	
	SimpleLoanService ls ;
	
	
	@BeforeEach
	void setUp() {
		ls= new SimpleLoanService();
	}
	
	@Test
	void isEligile1() {
		assertTrue(ls.isEligible(40, 30000000));
	}
	
	@Test
	void isEligile2() {
		assertFalse(ls.isEligible(14, 400000));
	}
	
	@Test
	void isEligile3() {
		assertFalse(ls.isEligible(14, 2500));
	}

	@Test
	void calculateEmi1() {
		assertEquals(1000, ls.calculateEMI(12000, 1));
	}
	
	@Test
	void calculateEmi2() {
		assertThrows(IllegalArgumentException.class, ()-> ls.calculateEMI(0, 1000));
	}
	
	@Test
	void calculateEmi3() {
		assertThrows(IllegalArgumentException.class, ()-> ls.calculateEMI(1000, 0));
	}
	
	@Test
	void groupTest() {
		assertAll(
	          () -> assertTrue(ls.isEligible(25, 24000)),
	          () -> assertEquals(10000, ls.calculateEMI(120000, 1)),
	          () -> assertEquals("Standard", ls.getLoan(700))
	      );
	}
	
	@Test
	void category1() {
		assertEquals("Premium", ls.getLoan(800));
	}
	
	@Test
	void category2() {
		assertEquals("Premium", ls.getLoan(700));
	}
	
	@Test
	void category3() {
		assertEquals("Premium", ls.getLoan(600));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}




