package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.CounterManagementDAO;
import com.turvo.abcbanking.dao.CustomerManagementDAO;
import com.turvo.abcbanking.dao.TokenManagementDAO;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.enums.TokenStatus;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.CustomerDetails;
import com.turvo.abcbanking.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CounterManagementServiceImpl implements CounterManagementService {
    private final static Logger LOG = LoggerFactory.getLogger(CounterManagementServiceImpl.class);

    @Autowired
    private CounterManagementDAO countermanagementDAO;
    @Autowired
    private CustomerManagementDAO customerManagementDAO;
    @Autowired
    private TokenManagementService tokenManagementService;
    @Autowired
    private TokenManagementDAO tokenManagementDAO;

    @Override
    public Counter addCounter(Counter counter) {
        return countermanagementDAO.save(counter);
    }

    @Override
    public List<Counter> getCounters() {
        return countermanagementDAO.findAll();
    }

    @Override
    public void operateCounter(int counterId) {
        Counter counter = countermanagementDAO.findOne(counterId);
        List<Token> tokens = counter.getTokens();
        Token activeToken = tokens.get(0);
        if (activeToken == null) {
            LOG.info("No Tokens to Process in Queue");
            return;
        }
        int customerId = activeToken.getCustomerId();
        CustomerDetails customerDetails = customerManagementDAO.findOne(customerId);
        List<ServicesOffered> servicesOffered = customerDetails.getServicesOpted();

        if (servicesOffered.size() > 1) {
            for (ServicesOffered servicesOffered1 : new ArrayList<>(servicesOffered)) {
                if (servicesOffered1.equals(ServicesOffered.WITHDRAW)) {
                    withdrawlOperation(customerId);
                } else if (servicesOffered1.equals(ServicesOffered.DEPOSIT)) {
                    depositOperation(customerId);
                } else if (servicesOffered1.equals(ServicesOffered.ENQUIRY)) {
                    enquiry(customerId);
                } else if (servicesOffered1.equals(ServicesOffered.ACC_OPENING)) {
                    accOpening(customerId);
                } else {
                    LOG.info("Wrong Service Opted");
                    throw new RuntimeException("Wrong Service Opted");
                }
                servicesOffered.remove(servicesOffered1);
                Counter newCounterAssigned = tokenManagementService.assignTokentoCounter(activeToken);
                activeToken.setTokenStatus(TokenStatus.FORWARDED);
                activeToken.setCounter(newCounterAssigned);
                tokenManagementDAO.save(activeToken);
            }
        } else if (servicesOffered.size() == 1) {
            ServicesOffered servicesOfferedObj = servicesOffered.get(0);
            if (servicesOfferedObj.equals(ServicesOffered.WITHDRAW)) {
                withdrawlOperation(customerId);
            } else if (servicesOfferedObj.equals(ServicesOffered.DEPOSIT)) {
                depositOperation(customerId);
            } else if (servicesOfferedObj.equals(ServicesOffered.ENQUIRY)) {
                enquiry(customerId);
            } else if (servicesOfferedObj.equals(ServicesOffered.ACC_OPENING)) {
                accOpening(customerId);
            } else {
                LOG.info("Wrong Service Opted");
                throw new RuntimeException("Wrong Service Opted");
            }
            activeToken.setTokenStatus(TokenStatus.COMPLETED);
            activeToken.setCounter(null);
            tokenManagementDAO.save(activeToken);
        }
    }

    private void withdrawlOperation(int customerId) {
        LOG.info("Withdrawal operation performed by: " + customerId);
    }

    private void depositOperation(int customerId) {
        LOG.info("Deposit Operation performed by: " + customerId);
    }

    private void enquiry(int customerId) {
        LOG.info("Enquiry about service by: " + customerId);
    }

    private void accOpening(int customerId) {
        LOG.info("Customer " + customerId + "Opened new account");
    }
}
