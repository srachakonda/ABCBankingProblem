package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.CounterManagementDAO;
import com.turvo.abcbanking.exception.ABCBankingException;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public class TokenCounterMapperServiceImpl implements TokenCounterMapperService {

    private final static Logger LOG = LoggerFactory.getLogger(TokenCounterMapperServiceImpl.class);

    @Autowired
    CounterManagementDAO countermanagementDAO;

    /**
     * @param token
     * @return
     */
    @Override
    public Token assignTokenToCounter(Token token) {
        List<Counter> counters = countermanagementDAO.findAll();
        if (counters.isEmpty()) {
            LOG.error("Counters not available");
            throw new ABCBankingException("Counters not available");
        }

        return null;
    }
}
