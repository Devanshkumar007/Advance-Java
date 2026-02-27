package com.lpu.BankingLoanApprovalSystem;

import org.springframework.stereotype.Component;

@Component
public class IncomeValidator implements LoanValidator {

	@Override
	public String validateLoan(double amount) {		
		return amount>2000?"INCOME VALIDATOR : APPROVED"
				:"INCOME VALIDATOR : REJECTED";
	}

}
