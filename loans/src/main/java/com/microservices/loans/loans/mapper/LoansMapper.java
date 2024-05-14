package com.microservices.loans.loans.mapper;

import com.microservices.loans.loans.dto.LoansDto;
import com.microservices.loans.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto maptoLoansDto(Loans loans, LoansDto loansDto){
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }

    public static Loans maptoLoans(LoansDto loansdto, Loans loans){
        loans.setMobileNumber(loansdto.getMobileNumber());
        loans.setLoanType(loansdto.getLoanType());
        loans.setTotalLoan(loansdto.getTotalLoan());
        loans.setAmountPaid(loansdto.getAmountPaid());
        loans.setOutstandingAmount(loansdto.getOutstandingAmount());
        return loans;
    }
}
