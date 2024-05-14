package com.microservices.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "account number should not be emplty or null")
    @Pattern(regexp = "($|[0-9]{10})")
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
