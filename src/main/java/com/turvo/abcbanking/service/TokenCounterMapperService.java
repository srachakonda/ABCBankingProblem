package com.turvo.abcbanking.service;

import com.turvo.abcbanking.model.Token;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface TokenCounterMapperService {

    Token assignTokenToCounter(Token token);
}
