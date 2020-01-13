package com.lendico.plangenerator.service;

import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lendico.plangenerator.model.LoanDetails;
import com.lendico.plangenerator.model.MonthlyPlan;
import com.lendico.plangenerator.model.RepaymentPlan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanGeneratorServiceTest {
	@InjectMocks
	PlanGeneratorService planGenerator;
	
	private LoanDetails loanDetails;
	double delta = 0.0;
	@Before
	public void setup() {
		//Provide initial setup here
		loanDetails = new LoanDetails();
		loanDetails.setDuration(24);
		loanDetails.setInterestRate(5);
		loanDetails.setLoanAmount(5000);
		loanDetails.setStartDate(ZonedDateTime.now());		
		
	}
	
	@Test
	public void testGeneratedPlans() {
		RepaymentPlan plan = planGenerator.generateMonthlyPlan(loanDetails);
		
		assertEquals(24, plan.getRepaymentPlan().size());
		
		MonthlyPlan firstMonthPlan = plan.getRepaymentPlan().get(0);
		MonthlyPlan lastMonthPlan = plan.getRepaymentPlan().get(23);
		
		assertEquals(219.36, firstMonthPlan.getBorrowerPaymentAmount(), delta);
        assertEquals(198.53, firstMonthPlan.getPrincipal(), delta);
        assertEquals(20.83, firstMonthPlan.getInterest(), delta);
        assertEquals(5000, firstMonthPlan.getInitialOutstandingPrincipal(), delta);
        assertEquals(4801.47, firstMonthPlan.getRemainingOutstandingPrincipal(), delta);

        assertEquals(219.28, lastMonthPlan.getBorrowerPaymentAmount(), delta);
        assertEquals(218.37, lastMonthPlan.getPrincipal(), delta);
        assertEquals(0.91, lastMonthPlan.getInterest(), delta);
        assertEquals(218.37, lastMonthPlan.getInitialOutstandingPrincipal(), delta);
        assertEquals(0, lastMonthPlan.getRemainingOutstandingPrincipal(), delta);
	}
	
	// Similarly we can write more test case for each and every functions in the service class.
}
