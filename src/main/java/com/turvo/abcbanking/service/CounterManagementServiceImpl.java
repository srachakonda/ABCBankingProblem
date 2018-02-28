package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.CounterManagementDAO;
import com.turvo.abcbanking.dao.CustomerManagementDAO;
import com.turvo.abcbanking.dao.TokenManagementDAO;
import com.turvo.abcbanking.enums.Role;
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

import java.util.ArrayList;
import java.util.List;

/**
 * To operate on counter related services
 *
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
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

    private boolean addActionItems = false;

    /**
     * Saves Counter to DB
     *
     * @param counter
     * @return
     */
    @Override
    public Counter addCounter(Counter counter) {
        return countermanagementDAO.save(counter);
    }

    /**
     * Returns list of counters available
     *
     * @return
     */
    @Override
    public List<Counter> getCounters() {
        return countermanagementDAO.findAll();
    }

    /**
     * To Operate on counter by Operator
     *
     * @param counterId
     */
    @Override
    public void operateCounter(long counterId) {
        Counter counter = countermanagementDAO.findOne(counterId);
        List<Token> tokens = counter.getTokens();
        Token activeToken = tokens.get(0);
        if (activeToken == null) {
            LOG.info("No Tokens to Process in Queue");
            return;
        }
        Long customerId = activeToken.getCustomerId();
        Customer customerDetails = customerManagementDAO.findOne(customerId);
        List<ServicesOffered> servicesOffered = customerDetails.getServicesOpted();

        if (servicesOffered.size() > 1) {
            for (ServicesOffered servicesOffered1 : new ArrayList<>(servicesOffered)) {
                if (servicesOffered1.equals(ServicesOffered.WITHDRAW)) {
                    withdrawlOperation(customerId);
                    activeToken = addComments("Withdrawal operation performed by customer", activeToken);
                } else if (servicesOffered1.equals(ServicesOffered.DEPOSIT)) {
                    depositOperation(customerId);
                } else if (servicesOffered1.equals(ServicesOffered.ENQUIRY)) {
                    enquiry(customerId);
                } else if (servicesOffered1.equals(ServicesOffered.ACC_OPENING)) {
                    accOpening(customerId);
                } else {
                    LOG.info("Wrong Service Opted");
                    throw new ABCBankingException();
                }
                servicesOffered.remove(servicesOffered1);
                Counter newCounterAssigned = tokenManagementService.assignTokenToCounter(activeToken);
                activeToken.setTokenStatus(TokenStatus.FORWARDED);
                activeToken.setCounter(newCounterAssigned);
                tokenManagementDAO.save(activeToken);
            }
        } else if (servicesOffered.size() == 1) {
            ServicesOffered servicesOfferedObj = servicesOffered.get(0);
            if (servicesOfferedObj.equals(ServicesOffered.WITHDRAW)) {
                withdrawlOperation(customerId);
                activeToken = addComments("Withdrawal operation performed by customer", activeToken);
                addActionItemstoCustomer(customerDetails, servicesOffered);
                customerManagementDAO.save(customerDetails);
            } else if (servicesOfferedObj.equals(ServicesOffered.DEPOSIT)) {
                depositOperation(customerId);
                activeToken = addComments("Deposit Operation performed by customer", activeToken);
            } else if (servicesOfferedObj.equals(ServicesOffered.ENQUIRY)) {
                enquiry(customerId);
            } else if (servicesOfferedObj.equals(ServicesOffered.ACC_OPENING)) {
                accOpening(customerId);
            } else {
                LOG.info("Wrong Service Opted");
                throw new ABCBankingException();
            }
            if (addActionItems) {
                Counter newCounterAssigned = tokenManagementService.assignTokenToCounter(activeToken);
                activeToken.setTokenStatus(TokenStatus.FORWARDED);
                activeToken.setCounter(newCounterAssigned);
                tokenManagementDAO.save(activeToken);
                addActionItems = false;
            } else {
                Role role = activeToken.getCounter().getOperator().getRole();
                if (role.equals(Role.OPERATOR) || role.equals(Role.MANAGER)) {
                    activeToken.setTokenStatus(TokenStatus.COMPLETED);
                } else {
                    Counter newCounterAssigned = tokenManagementService.assignTokenToCounter(activeToken);
                    activeToken.setTokenStatus(TokenStatus.FORWARDED);
                    activeToken.setCounter(newCounterAssigned);
                    tokenManagementDAO.save(activeToken);
                    addActionItems = true;
                }
                activeToken.setCounter(null);
            }
            tokenManagementDAO.save(activeToken);
        }
    }

    /**
     * Operator can add extra action items to customer if needed
     *
     * @param customerDetails
     * @param servicesOpted
     * @return
     */
    private Customer addActionItemstoCustomer(Customer customerDetails, List<ServicesOffered> servicesOpted) {
        addActionItems = true;
        servicesOpted.add(ServicesOffered.ENQUIRY);
        customerDetails.setServicesOpted(servicesOpted);
        return customerDetails;
    }

    /**
     * Operator can add comments on token
     *
     * @param comments
     * @param token
     * @return
     */
    private Token addComments(String comments, Token token) {
        String existingComments = token.getComments();
        if (existingComments.isEmpty() || existingComments == null) {
            token.setComments(comments);
        } else {
            StringBuffer existingCommentsBuffer = new StringBuffer(existingComments);
            existingCommentsBuffer.append(comments);
            token.setComments(existingCommentsBuffer.toString());
        }
        return token;
    }

    /**
     * Withdrawl operation and can add more business logic here as needed
     *
     * @param customerId
     */
    private void withdrawlOperation(long customerId) {
        LOG.info("Withdrawal operation performed by: " + customerId);
    }

    /**
     * Deposit operation and can add more business logic here as needed
     *
     * @param customerId
     */
    private void depositOperation(long customerId) {
        LOG.info("Deposit Operation performed by: " + customerId);
    }

    /**
     * Enquiry operation and can add more business logic here as needed
     *
     * @param customerId
     */
    private void enquiry(long customerId) {
        LOG.info("Enquiry about service by: " + customerId);
    }

    /**
     * Acc opening operation and can add more business logic here as needed
     *
     * @param customerId
     */
    private void accOpening(long customerId) {
        LOG.info("Customer " + customerId + "Opened new account");
    }
}
