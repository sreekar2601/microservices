package com.microservices.loans.loans.services.impl;

import com.microservices.loans.loans.constants.LoansConstants;
import com.microservices.loans.loans.dto.LoansDto;
import com.microservices.loans.loans.entity.Loans;
import com.microservices.loans.loans.exception.LoanAlreadyExistsException;
import com.microservices.loans.loans.exception.ResourceNotFoundException;
import com.microservices.loans.loans.mapper.LoansMapper;
import com.microservices.loans.loans.repository.LoansRepository;
import com.microservices.loans.loans.services.ILoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    @Autowired
    LoansRepository loansRepository;

    public void createLoan(String mobileNumber){

        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    public Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDto getLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).
                orElseThrow(()-> new ResourceNotFoundException("Loan Not Found"));

        return LoansMapper.maptoLoansDto(loans,new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).
                orElseThrow(()-> new ResourceNotFoundException("Loan Not Found"));
        LoansMapper.maptoLoans(loansDto,loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).
                orElseThrow(()->new ResourceNotFoundException("Loan Not Found"));
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
