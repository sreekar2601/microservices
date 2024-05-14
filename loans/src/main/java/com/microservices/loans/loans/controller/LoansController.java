package com.microservices.loans.loans.controller;

import com.microservices.loans.loans.constants.LoansConstants;
import com.microservices.loans.loans.dto.LoansBuildDataInfo;
import com.microservices.loans.loans.dto.LoansDto;
import com.microservices.loans.loans.dto.ResponseDto;
import com.microservices.loans.loans.services.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

    private static final Logger log = LoggerFactory.getLogger(LoansController.class);
    @Autowired
    ILoansService iLoansService;

    @Value("${build.version}")
    private String buildInfo;

    @Autowired
    LoansBuildDataInfo loansBuildDataInfo;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createLoan(@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") @RequestParam String mobileNumber){
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<LoansDto> fetchLoans(@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") @RequestParam String mobileNumber){
        LoansDto loansDto = iLoansService.getLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto){
        boolean isUpdated = iLoansService.updateLoan(loansDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") @RequestParam String mobileNumber){
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(buildInfo);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansBuildDataInfo> getContactInfo(){
        log.debug("Invoked Loans contact-info API");
        return ResponseEntity.status(HttpStatus.OK).body(loansBuildDataInfo);
    }
}
