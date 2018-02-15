package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.TokenManagementDAO;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.model.CustomerDetails;
import com.turvo.abcbanking.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TokenManagementServiceImpl implements TokenManagementService {
    private final static Logger LOG = LoggerFactory.getLogger(TokenManagementServiceImpl.class);

    @Autowired
    CustomerManagementService customerManagementService;


    @Autowired
    TokenManagementDAO tokenManagementDAO;

    @Override
    public Token generateToken(CustomerDetails customerDetails) {
        LOG.info("In generateToken method");
        //TODO
        //check if new customer if yes save customer details and proceed further
        if (true) {
            customerManagementService.saveCustomer(customerDetails);
        }

        Token token = issueToken(customerDetails);
//        TODO
        //add to queue for getting it serviced by counters and return response token object
        return token;
    }

    private Token issueToken(CustomerDetails customerDetails) {
        LOG.info("In issueToken method");
        List<ServicesOffered> servicesOpted = customerDetails.getServicesOpted();
        if (StringUtils.isEmpty(customerDetails.getServicesOpted())) {
            LOG.info("No Services opted by customer");
            throw new IllegalArgumentException("No Services Selected by customer");
        }
        Token token = new Token();
        token.setPriority(customerDetails.getAccountType());
        token.setServicesOpted(servicesOpted);
        //TODO
        //save generated token and return back to request
//        tokenManagementDAO.save(token);
        return token;
    }
}
