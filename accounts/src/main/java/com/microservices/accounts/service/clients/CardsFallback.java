package com.microservices.accounts.service.clients;

import com.microservices.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardDto> fetchCard(String mobileNumber) {
        return null;
    }
}
