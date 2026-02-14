package com.assessment.service;

public class SimpleLoanService {
	
	public boolean isEligible(int age, double sal) {
		if(age>=21 && age<=60 && sal>=25000) return true;
		return false;
	}
	
	public double calculateEMI(double loanAmount, int tenureYears) {
		if(loanAmount<=0) throw new IllegalArgumentException("Loan AMount cannot be less than 0");
		if(tenureYears <= 0) throw new IllegalArgumentException("Tenure cannot be less than 0");
		
		return loanAmount /(tenureYears * 12) ;
	}
	
	public String getLoan(int creditScore) {
		if(creditScore>=750) return "Premium";
		if(creditScore>=600 && creditScore<750) return "Standard";
		return "High Risk" ;
	}
}
