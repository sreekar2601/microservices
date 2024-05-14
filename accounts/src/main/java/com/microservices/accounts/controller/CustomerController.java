package com.microservices.accounts.controller;

import com.microservices.accounts.dto.CustomerDetailsDto;
import com.microservices.accounts.service.ICustomerService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@RequestParam @Pattern(regexp = "($|[0-9]{10})") String mobileNumber){
        CustomerDetailsDto customerDetailsDto=iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
