package com.lendico.plangenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendico.plangenerator.model.LoanDetails;
import com.lendico.plangenerator.model.RepaymentPlan;
import com.lendico.plangenerator.service.PlanGeneratorService;
import com.lendico.plangenerator.utilities.UserInputValidation;

@RestController
@RequestMapping("/plan-generator")
public class PlangeneratorController {
	
	@Autowired
	UserInputValidation validation;
	@Autowired
	PlanGeneratorService planGeneratorService;

	/**
	 * This method will call the service layer - generateMonthlyPlan method to 
	 * fetch the detailed monthly plan.
	 * @param loanDetails
	 * @return complete repayment plan
	 */
	@PostMapping
	public ResponseEntity<RepaymentPlan> generatePlan(@RequestBody LoanDetails loanDetails) {
		validation.checkUserInputs(loanDetails);
		RepaymentPlan repaymentPlan = planGeneratorService.generateMonthlyPlan(loanDetails);
		return new ResponseEntity<>(repaymentPlan, HttpStatus.OK);
	}
}