package com.soapService.demo.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soapService.demo.api.eligibilitycheck.Acknowledgement;
import com.soapService.demo.api.eligibilitycheck.CustomerRequest;

@Service
public class EligibilityCheckService {
	
	public Acknowledgement checkEligibility(CustomerRequest request) {
		
		Acknowledgement acknowledgement = new Acknowledgement();
		
		List<String> mismatchCriteriaList = acknowledgement.getCriteriaMismatch();
		
		if(!(request.getAge()>25 && request.getAge()<60)) {
			mismatchCriteriaList.add("Applicant Age should be between 25 and 60 years.");
		}
		if(!(request.getAnnualIncome()>20000)) {
			mismatchCriteriaList.add("Minimum annual income should be 20000");
		}
		if(!(request.getCibilScore()>250)) {
			mismatchCriteriaList.add("Low Cibil score. Please try after 6 months.");
		}
		
		if(mismatchCriteriaList.size()>0) {
			acknowledgement.setApprovedAmount(0);
			acknowledgement.setIsEligible(false);
		}
		else {
			acknowledgement.setApprovedAmount(1000000);
			acknowledgement.setIsEligible(true);
		}
		return acknowledgement;
	}
}
