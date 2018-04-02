package com.turvo.abcbanking.counter;

import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.service.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 */
public class CounterFactory {
    private final static Logger LOG = LoggerFactory.getLogger(CounterFactory.class);

    /**
     * Creates a counter based on the services required .
     *
     * @param counter
     * @param tokenService
     * @return
     */

    public Receiver createCounterInstance(Counter counter, TokenManagementService tokenService) {
        LOG.info("Inside create counter instance method");
        Receiver receivingCounter = null;

        String counterName = counter.getCounterName();

        // Sorting the list of operations in a lexicographic manner
        List<ServicesOffered> operationList = new ArrayList<>();

        operationList.addAll(counter.getServicesOffered());

        Collections.sort(operationList);

        String counterOperation = String.join(",", operationList.toString());

        String counterType = counter.getAccountType().toString();

        int counterId = Math.toIntExact(counter.getId());

        //The service types or operations specified in switch case are to be specified in lexicographic manner

        switch (counterOperation) {

            case "DEPOSIT":
                receivingCounter = new DepositCounter(counterId, counterName, counterType, tokenService);
                break;

            case "WITHDRAW":
                receivingCounter = new WithdrawalCounter(counterId, counterName, counterType, tokenService);
                break;

            case "DEPOSIT,WITHDRAW":
                receivingCounter = new DepositAndWithdrawCounter(counterId, counterName, counterType, tokenService);
                break;
        }
        return receivingCounter;

    }

}
