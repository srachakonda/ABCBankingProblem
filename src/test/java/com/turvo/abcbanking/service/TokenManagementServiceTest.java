package com.turvo.abcbanking.service;

import com.turvo.abcbanking.ABCBankingApplication;
import com.turvo.abcbanking.dao.CounterManagementDAO;
import com.turvo.abcbanking.dao.TokenManagementDAO;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.enums.TokenStatus;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Customer;
import com.turvo.abcbanking.model.Token;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ABCBankingApplication.class)
public class TokenManagementServiceTest {

    @Autowired
    private TokenManagementService tokenManagementService;

    @MockBean
    private TokenManagementDAO tokenManagementDAO;
    @MockBean
    private CustomerManagementService customerManagementService;
    @MockBean
    private CounterManagementDAO counterManagementDAO;

    @Test
    public void testGenerateToken() {
        Customer customer = new Customer();
        customer.setAccountType(AccountType.PRIORITY);
        customer.setAddress("Hyderabad");
        customer.setName("Sampath");
        customer.setPhoneNumber("123456789");
        customer.setServicesOpted(Arrays.asList(ServicesOffered.ENQUIRY));
        customer.setCustomerId(1L);

        Token token = new Token();
        token.setPriority(customer.getAccountType());
        token.setCustomerId(customer.getCustomerId());
        token.setTokenStatus(TokenStatus.COMPLETED);
        List<Token> tokens = new ArrayList<>();
        tokens.add(token);

        Counter counter = new Counter();
        counter.setAccountType(AccountType.PRIORITY);

        List<Counter> counters = new ArrayList<>();
        counters.add(counter);

        Mockito.when(tokenManagementDAO.findByCustomerId(customer.getCustomerId())).thenReturn(tokens);
        Mockito.when(customerManagementService.saveCustomer(customer)).thenReturn(true);
        Mockito.when(counterManagementDAO.findByAccountType(AccountType.PRIORITY)).thenReturn(counters);
        Mockito.when(tokenManagementDAO.save(token)).thenReturn(token);
        Token resultToken = tokenManagementService.generateToken(customer);
        Assert.assertEquals(resultToken.getPriority(), AccountType.PRIORITY);
    }
}
