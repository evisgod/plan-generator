package com.lendico.plangenerator.service;

import com.lendico.plangenerator.model.LoanDetails;
import com.lendico.plangenerator.model.RepaymentPlan;

@FunctionalInterface
public interface PlanGenerator {
	
	RepaymentPlan generateMonthlyPlan(LoanDetails loanDetails);
}
