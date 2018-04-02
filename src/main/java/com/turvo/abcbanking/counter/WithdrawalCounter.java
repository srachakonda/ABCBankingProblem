package com.turvo.abcbanking.counter;

import com.turvo.abcbanking.model.Token;
import com.turvo.abcbanking.service.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class WithdrawalCounter extends AbstractCounter implements Receiver {

    private final static Logger LOG = LoggerFactory.getLogger(WithdrawalCounter.class);

    @Autowired
    TokenManagementService tokenManagementService;

    public WithdrawalCounter(int counterId, String counterName, String counterType,
                             TokenManagementService tokenService) {
        this.counterId = counterId;
        this.counterType = counterType;
        this.counterName = counterName;
        this.tokenService = tokenService;

    }

    @Override
    public void receiveToken(Token tokenDTO) throws Exception {
        LOG.info("Received message: " + tokenDTO + " at counter: " + counterName);
        tokenManagementService.assignTokenToCounter(tokenDTO);
        updateTokenStatusAsInProgress(tokenDTO);
        serveToken();
        updateTokenComments(tokenDTO, "Performed Withdrawal operation");
        updateTokenStatusAsCompleted(tokenDTO);
    }

    private void serveToken() throws InterruptedException {
        LOG.info("Inside servetoken method");
        TimeUnit.MILLISECONDS.sleep(6000);
    }

}
