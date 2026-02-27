package com.lpu.BankingLoanApprovalSystem;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
@Scope("prototype")
public class CreditScoreValidator implements LoanValidator{

	@Override
	public String validateLoan(double amount) {
		return amount>=1000?"CREDIT SCORE VALIDATION :"
				+ " APPROVED":"CREDIT SCORE VALIDATION :REJECTED";
	}
	
}
