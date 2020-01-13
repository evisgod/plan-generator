package com.lendico.plangenerator.model;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LoanDetails {
	@NotNull(message = "loanAmount is required")
	@Positive
	private double loanAmount;
	@NotNull(message = "Interest Rate is required")
	@Positive
	private double interestRate;
	@NotNull(message = "loanAmount is required")
	private int duration;
	@NotNull(message = "Start Date is required")
	private ZonedDateTime startDate;
	
	public LoanDetails() {
		super();
	}
	
	public LoanDetails(@NotNull(message = "loanAmount is required") @Positive double loanAmount,
			@NotNull(message = "Interest Rate is required") @Positive double interestRate,
			@NotNull(message = "loanAmount is required") int duration,
			@NotNull(message = "Start Date is required") ZonedDateTime startDate) {
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.duration = duration;
		this.startDate = startDate;
	}



	public double getLoanAmount() {
		return loanAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public int getDuration() {
		return duration;
	}

	public ZonedDateTime getStartDate() {
		return startDate;
	}
	
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setStartDate(ZonedDateTime startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "LoanDetails [loanAmount=" + loanAmount + ", interestRate=" + interestRate + ", duration=" + duration
				+ ", startDate=" + startDate + "]";
	}
}