package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.CounterManagementDAO;
import com.turvo.abcbanking.dao.TokenManagementDAO;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.enums.TokenStatus;
import com.turvo.abcbanking.exception.ABCBankingException;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Customer;
import com.turvo.abcbanking.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
@Service
public class TokenManagementServiceImpl implements TokenManagementService {
    private final static Logger LOG = LoggerFactory.getLogger(TokenManagementServiceImpl.class);

    @Autowired
    CustomerManagementService customerManagementService;

    @Autowired
    TokenManagementDAO tokenManagementDAO;

    @Autowired
    CounterManagementDAO countermanagementDAO;

    PriorityQueue<Counter> counters;
    List<Token> tokens = new LinkedList<Token>();

    /**
     * Generates Token and forwards to assign it to counter available
     * @param customer
     * @return
     */
    @Override
    public Token generateToken(Customer customer) {
        LOG.info("In generateToken method");
        if (customer.isNewCustomer()) {
            customerManagementService.saveCustomer(customer);
        }
        //check if customer have token in_progress state. If then throw exception stating that he already have one token in pending state
        List<Token> tokens = tokenManagementDAO.findByCustomerId(customer.getCustomerId());
        for (Token token : tokens) {
            if (token.getTokenStatus().equals(TokenStatus.IN_PROGRESS) || token.getTokenStatus().equals(TokenStatus.IN_QUEUE))
                throw new ABCBankingException("Cannot create new token when one token is in progress");
        }
        Token token = issueToken(customer);
        token.setCounter(assignTokenToCounter(token));
        tokenManagementDAO.save(token);
        return token;
    }

    /**
     * Assigns token to respective counter based on services requested
     * @param token
     * @return
     */
    @Override
    public Counter assignTokenToCounter(Token token) {
        LOG.info("In assign token to counter method");
        Counter counterToAssign = getCountertoAssign(token.getPriority());
        int minIndex = 0;
        if (!CollectionUtils.isEmpty(counterToAssign.getTokens())) {
            minIndex = counterToAssign.getTokens().size();
        }
        if (minIndex == 0 && counterToAssign.getTokens() == null) {
            List<Token> counterTokens = new ArrayList<>();
            counterTokens.add(minIndex, token);
            counterToAssign.setTokens(counterTokens);
        } else {
            counterToAssign.getTokens().add(minIndex, token);
        }
        LOG.info("Assigning token" + token.toString() + " to counter: {}" + counterToAssign.toString());
        return counterToAssign;
    }

    /**
     * Returns counter to assign to token based on business logic
     * @param accountType
     * @return
     */
    private Counter getCountertoAssign(AccountType accountType) {
        LOG.info("In Get Counter to Assign method");
        List<Counter> counters = countermanagementDAO.findByAccountType(accountType);

        if (CollectionUtils.isEmpty(counters)) {
            LOG.info("No counter available serves this type of service");
            throw new ABCBankingException("No counter available serves this type of service");
        }

        if (counters.size() == 1) {
            return counters.get(0);
        }

        Map<Counter, Integer> counterRankMap = new HashMap<>();
        for (Counter counter : counters) {
            int noOfTokensAssigned = 0;
            if (!CollectionUtils.isEmpty(counter.getTokens())) {
                noOfTokensAssigned = counter.getTokens().size();
            }
            counterRankMap.put(counter, noOfTokensAssigned);
        }
        Map.Entry<Counter, Integer> min = null;
        for (Map.Entry<Counter, Integer> entry : counterRankMap.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }

        return min.getKey();
    }

    /**
     * Issues Token by creating new Token based on customer details
     * @param customerDetails
     * @return
     */
    private Token issueToken(Customer customerDetails) {
        LOG.info("In issueToken method");
        List<ServicesOffered> servicesOpted = customerDetails.getServicesOpted();
        if (StringUtils.isEmpty(customerDetails.getServicesOpted())) {
            LOG.info("No Services opted by customer");
            throw new IllegalArgumentException("No Services Selected by customer");
        }
        Token token = new Token();
        token.setPriority(customerDetails.getAccountType());
        token.setCustomerId(customerDetails.getCustomerId());
        return token;
    }
}
