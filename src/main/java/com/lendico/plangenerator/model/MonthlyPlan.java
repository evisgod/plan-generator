package com.lendico.plangenerator.model;

import java.time.ZonedDateTime;


public class MonthlyPlan {
	private double borrowerPaymentAmount;
	private ZonedDateTime date;
	private double initialOutstandingPrincipal;
	private double interest;
	private double principal;
	private double remainingOutstandingPrincipal;

	/**
	 * @param annuity
	 * @param zonedDateTime
	 * @param loanAmount
	 * @param interest2
	 * @param principal2
	 * @param d
	 */
	public MonthlyPlan(double annuity, ZonedDateTime zonedDateTime, double loanAmount, double interest2,
			double principal2, double d) {
		super();
		this.borrowerPaymentAmount = annuity;
		this.date = zonedDateTime;
		this.initialOutstandingPrincipal = loanAmount;
		this.interest = interest2;
		this.principal = principal2;
		this.remainingOutstandingPrincipal = d;
	}

	public double getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public double getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}

	public double getInterest() {
		return interest;
	}

	public double getPrincipal() {
		return principal;
	}

	public double getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}

	@Override
	public String toString() {
		return "MonthlyPlan [borrowerPaymentAmount=" + borrowerPaymentAmount + ", date=" + date
				+ ", initialOutstandingPrincipal=" + initialOutstandingPrincipal + ", interest=" + interest
				+ ", principal=" + principal + ", remainingOutstandingPrincipal=" + remainingOutstandingPrincipal + "]";
	}
}