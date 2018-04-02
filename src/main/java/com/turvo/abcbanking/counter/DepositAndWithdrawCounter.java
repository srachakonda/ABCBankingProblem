package com.turvo.abcbanking.counter;

import com.turvo.abcbanking.dao.CustomerManagementDAO;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.model.Customer;
import com.turvo.abcbanking.model.Token;
import com.turvo.abcbanking.service.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 */
public class DepositAndWithdrawCounter extends AbstractCounter implements Receiver {
    private final static Logger LOG = LoggerFactory.getLogger(DepositAndWithdrawCounter.class);

    @Autowired
    private CustomerManagementDAO customerManagementDAO;

    @Autowired
    private TokenManagementService tokenManagementService;

    public DepositAndWithdrawCounter(int counterId, String counterName, String counterType, TokenManagementService tokenService) {
        this.counterId = counterId;
        this.counterName = counterName;
        this.counterType = counterType;
        this.tokenService = tokenService;

    }

    @Override
    public void receiveToken(Token token) throws Exception {
        LOG.info("Received message: " + token + " at counter: " + counterName);
        tokenManagementService.assignTokenToCounter(token);
        updateTokenStatusAsInProgress(token);
        serveToken(token);
        updateTokenStatusAsCompleted(token);
    }

    private void serveToken(Token token) throws InterruptedException {

        TimeUnit.MINUTES.sleep(1);

        Long customerId = token.getCustomerId();
        Customer customerDetails = customerManagementDAO.findOne(customerId);
        List<ServicesOffered> servicesOffered = customerDetails.getServicesOpted();
        if (servicesOffered.contains(ServicesOffered.DEPOSIT)) {
            LOG.info(" Performing Deposit operation.");
            performDeposit();
            token.setComments(token.getComments() + " Performed Deposit operation.");
        }

        if (servicesOffered.contains(ServicesOffered.WITHDRAW)) {
            LOG.info(" Performing withdrawal operation.");
            performWithdrawal();
            token.setComments(token.getComments() + " Performed Withdrawal operation.");
        }
    }

    public void performDeposit() {
        LOG.info(" Performing Deposit operation.");
    }

    public void performWithdrawal() {
        LOG.info(" Performing Withdrawal operation.");
    }
}
