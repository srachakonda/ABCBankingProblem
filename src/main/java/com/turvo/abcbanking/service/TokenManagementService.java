package com.turvo.abcbanking.service;


import com.turvo.abcbanking.model.CustomerDetails;
import com.turvo.abcbanking.model.Token;

public interface TokenManagementService {

    public Token generateToken(CustomerDetails customerDetails);
}
