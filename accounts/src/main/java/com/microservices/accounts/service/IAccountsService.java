package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Customer;

import java.util.Optional;

public interface IAccountsService {

    public void createAccount(CustomerDto customerDto);

    CustomerDto getAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
