package com.turvo.abcbanking.service;

import com.turvo.abcbanking.ABCBankingApplication;
import com.turvo.abcbanking.dao.CounterManagementDAO;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.Role;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.enums.TokenStatus;
import com.turvo.abcbanking.model.Branch;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Operator;
import com.turvo.abcbanking.model.Token;
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
public class CounterManagementServiceTest {
    @Autowired
    private CounterManagementService counterManagementService;
    @MockBean
    private CounterManagementDAO counterManagementDAO;
    private Branch branch = new Branch("Jubilee Hills", 1L);
    private List<ServicesOffered> servicesOffered = new ArrayList<>(Arrays.asList(ServicesOffered.DEPOSIT, ServicesOffered.WITHDRAW));
    private Operator operator = new Operator(Role.MANAGER, branch);

    List<Token> tokens = new ArrayList<>(Arrays.asList(new Token(TokenStatus.IN_QUEUE, AccountType.PRIORITY, "test")));

    private Counter counter = new Counter(servicesOffered, AccountType.PRIORITY, branch, operator, tokens);

    @Test
    public void testAddCounter() {
        Mockito.when(counterManagementDAO.save(counter)).thenReturn(counter);
    }
}
