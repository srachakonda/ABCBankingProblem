package com.turvo.abcbanking.service;


import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Customer;
import com.turvo.abcbanking.model.Token;

import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface TokenManagementService {

    public Token generateToken(Customer customer);

    public Counter assignTokenToCounter(Token token);

}
