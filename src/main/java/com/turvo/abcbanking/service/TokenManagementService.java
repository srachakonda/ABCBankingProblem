package com.turvo.abcbanking.service;


import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Customer;
import com.turvo.abcbanking.model.Token;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface TokenManagementService {

    Token generateToken(Customer customer);

    Counter assignTokenToCounter(Token token);

}
