package com.microservices.accounts.service.clients;

import com.microservices.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoansDto> fetchLoans(String mobileNumber) {
        return null;
    }
}
