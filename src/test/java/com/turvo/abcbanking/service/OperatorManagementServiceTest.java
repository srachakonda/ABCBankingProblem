package com.turvo.abcbanking.service;

import com.turvo.abcbanking.ABCBankingApplication;
import com.turvo.abcbanking.dao.OperatorManagementDAO;
import com.turvo.abcbanking.enums.Role;
import com.turvo.abcbanking.model.Operator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ABCBankingApplication.class)
public class OperatorManagementServiceTest {

    @Autowired
    private OperatorManagementService operatorManagementService;

    @MockBean
    private OperatorManagementDAO operatorManagementDAO;


    @Test
    public void testAddOperator() {
        Operator operator = new Operator();
        operator.setRole(Role.MANAGER);
        operator.setId(1L);
        Mockito.when(operatorManagementDAO.save(operator)).thenReturn(operator);
        Operator resultOperator = operatorManagementService.addOperator(operator);
        Assert.assertTrue(resultOperator instanceof Operator);
        Assert.assertEquals(operator.getRole(), Role.MANAGER);
    }

    @Test
    public void testGetOperators() {
        Operator operator = new Operator();
        operator.setRole(Role.MANAGER);
        operator.setId(1L);
        List<Operator> operators = new ArrayList<>();
        operators.add(operator);
        Mockito.when(operatorManagementDAO.findAll()).thenReturn(operators);
        List<Operator> resultOperators = operatorManagementService.getOperators();
        Assert.assertEquals(resultOperators.get(0).getRole(), operators.get(0).getRole());
    }
}
