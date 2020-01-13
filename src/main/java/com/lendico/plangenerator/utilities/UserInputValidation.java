package com.lendico.plangenerator.utilities;

import org.springframework.stereotype.Component;

import com.lendico.plangenerator.exception.PlanGenerationException;
import com.lendico.plangenerator.model.LoanDetails;

@Component
public class UserInputValidation {

	public void checkUserInputs(LoanDetails loanDetails) {
		if(loanDetails.getDuration() <= 0 || 
				loanDetails.getInterestRate() <= 0 || 
				loanDetails.getLoanAmount() <= 0 ||
				loanDetails.getStartDate() == null)  {
			// we can be more specific here..
			throw new PlanGenerationException("Please enter a valid input..");
		}
	}
	
}	
