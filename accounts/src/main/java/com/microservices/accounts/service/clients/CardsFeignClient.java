package com.microservices.accounts.service.clients;

import com.microservices.accounts.dto.CardDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards",fallback = CardsFallback.class)
public interface CardsFeignClient {

    @GetMapping("/api/fetch")
    public ResponseEntity<CardDto> fetchCard(@RequestParam String mobileNumber);
}
