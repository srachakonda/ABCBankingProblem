package com.turvo.abcbanking.counter;

import com.turvo.abcbanking.model.Token;
import com.turvo.abcbanking.service.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 */
public class DepositCounter extends AbstractCounter implements Receiver {

    private final static Logger LOG = LoggerFactory.getLogger(DepositCounter.class);

    @Autowired
    TokenManagementService tokenManagementService;

    public DepositCounter(int counterId, String counterName, String counterType, TokenManagementService tokenService) {
        this.counterId = counterId;
        this.counterType = counterType;
        this.counterName = counterName;
        this.tokenService = tokenService;

    }

    @Override
    public void receiveToken(Token token) throws Exception {

        LOG.info("Received message: " + token + " at counter: " + counterName);
        tokenManagementService.assignTokenToCounter(token);
        updateTokenStatusAsInProgress(token);
        serveToken();
        updateTokenComments(token, "Performed Deposit operation.");
        updateTokenStatusAsCompleted(token);
    }

    private void serveToken() throws InterruptedException {
        LOG.info("Inside servetoken method");
        TimeUnit.MILLISECONDS.sleep(6000);
    }

}
