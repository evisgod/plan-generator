package com.lendico.plangenerator.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lendico.plangenerator.config.Constants;
import com.lendico.plangenerator.model.LoanDetails;
import com.lendico.plangenerator.model.MonthlyPlan;
import com.lendico.plangenerator.model.RepaymentPlan;

@Service
public class PlanGeneratorService implements PlanGenerator {

	@Override
	public RepaymentPlan generateMonthlyPlan(LoanDetails loanDetails) {
		List<MonthlyPlan> monthlyPlan = new LinkedList<>();

		double loanAmount = loanDetails.getLoanAmount();
		int duration = loanDetails.getDuration();
		double interestRate = calculateInterestRate(loanDetails.getInterestRate());
		double annuity = calculateAnnuity(loanAmount, duration, interestRate);

		for (int i = 0; i < loanDetails.getDuration(); i++) {
			double installmentInterest = calculateInterest(loanDetails.getInterestRate(), loanAmount);
			double principal = calculatePrincipal(annuity, installmentInterest, loanAmount);
			monthlyPlan.add(
					new MonthlyPlan(actualAnnuity(principal, installmentInterest), 
									loanDetails.getStartDate().plusMonths(i), 
									roundOff(loanAmount),
									installmentInterest, 
									principal, 
									calculateRemainingOutStanding(loanAmount, principal)));
			loanAmount = loanAmount - principal;
		}
		return new RepaymentPlan(monthlyPlan);
	}

	private double calculateRemainingOutStanding(double loanAmount, double principal) {
		if(principal > loanAmount) {
			return 0;
		}
		return roundOff(loanAmount - principal);	
	}

	private double calculatePrincipal(double annuity, double installmentInterest, double loanAmount) {
		double principal = annuity - installmentInterest;
		if(principal > loanAmount) {
			return roundOff(loanAmount);
		}
		return roundOff(annuity - installmentInterest);
	}

	private double calculateInterestRate(double rate) {
		return rate / (Constants.MONTHS_PER_YEAR * 100);
	}

	private double calculateInterest(double interestRate, double loanAmount) {
		double installmentInterest = (interestRate * loanAmount * Constants.DAYS_PER_MONTH)
				/ (Constants.DAYS_PER_YEAR * Constants.CENTS_PER_COIN);
		return roundOff(installmentInterest);
	}

	private double actualAnnuity(double principal, double interest) {
		return roundOff(principal + interest);
	}
	
	private double calculateAnnuity(double loanAmount, int duration, double interestRate) {
		double divisor = 1 - Math.pow((1 + interestRate), -1 * duration);
		double annuity = (loanAmount * interestRate) / divisor;
		return roundOff(annuity);
	}

	/**
	 * This method rounds off up to 2 decimals, used BigDecimal for money.
	 * @param value
	 * @return
	 */
	private double roundOff(double value) {
		BigDecimal bigValue = BigDecimal.valueOf(value);
		return Math.round(bigValue.multiply(BigDecimal.valueOf(Constants.CENTS_PER_COIN)).doubleValue()) / Constants.CENTS_PER_COIN;
	}
}