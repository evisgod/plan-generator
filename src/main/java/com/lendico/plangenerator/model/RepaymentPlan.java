package com.lendico.plangenerator.model;

import java.util.List;

public class RepaymentPlan {
	private List<MonthlyPlan> repaymentPlan;

	public RepaymentPlan(List<MonthlyPlan> repaymentPlan) {
		this.repaymentPlan = repaymentPlan;
	}

	public List<MonthlyPlan> getRepaymentPlan() {
		return repaymentPlan;
	}

	@Override
	public String toString() {
		return "RepaymentPlan [repaymentPlan=" + repaymentPlan + "]";
	}	
}