package com.microservices.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class CustomerDto {

    @NotEmpty(message = "name should not be null or empty")
    @Size(min=5,max = 30,message = "size valid kadura ra howle")
    private String name;
    @NotEmpty(message = "email should not be not or empty")
    @Email(message = "email should be valid format")
    private String email;
    @Pattern(regexp = "($|[0-9]{10})")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
