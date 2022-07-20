package com.soapService.demo.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soapService.demo.api.eligibilitycheck.Acknowledgement;
import com.soapService.demo.api.eligibilitycheck.CustomerRequest;
import com.soapService.demo.api.service.EligibilityCheckService;

@Endpoint
public class EligibilityEndpoint {

	private static final String NAMESPACE = "http://www.soapService.com/demo/api/eligibilityCheck";
	
	@Autowired
	private EligibilityCheckService ecService;
	
	@PayloadRoot(namespace=NAMESPACE, localPart="CustomerRequest")
	@ResponsePayload
	public Acknowledgement checkEligibility(@RequestPayload CustomerRequest request) {
		return ecService.checkEligibility(request);
	}
}
