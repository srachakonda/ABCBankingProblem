package com.turvo.abcbanking.service;


import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.CustomerDetails;
import com.turvo.abcbanking.model.Token;

import java.util.List;

public interface TokenManagementService {

    public Token generateToken(CustomerDetails customerDetails);

    public List<Token> tokenStatuses();

    public Counter assignTokentoCounter(Token token);

}
