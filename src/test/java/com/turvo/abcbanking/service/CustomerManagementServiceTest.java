package com.turvo.abcbanking.service;

import com.turvo.abcbanking.ABCBankingApplication;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.exception.ABCBankingException;
import com.turvo.abcbanking.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ABCBankingApplication.class)
public class CustomerManagementServiceTest {
    private CustomerManagementService customerManagementService = Mockito.mock(CustomerManagementService.class);

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setAccountType(AccountType.PRIORITY);
        customer.setAddress("Hyderabad");
        customer.setName("Sampath");
        customer.setPhoneNumber("123456789");
        customer.setServicesOpted(Arrays.asList(ServicesOffered.ENQUIRY));
        Mockito.when(customerManagementService.saveCustomer(customer)).thenReturn(true);
        boolean resultCustomer = customerManagementService.saveCustomer(customer);
        Assert.assertEquals(resultCustomer, true);
    }

    @Test(expected = ABCBankingException.class)
    public void testSaveCustomerFailure() throws ABCBankingException {
        Customer customer = new Customer();
        customer.setAccountType(AccountType.REGULAR);
        customer.setAddress("Secunderabad");
        customer.setName("Naveen");
        customer.setPhoneNumber("9849584548");
        customer.setServicesOpted(Arrays.asList(ServicesOffered.DEPOSIT));
        Mockito.when(customerManagementService.saveCustomer(customer)).thenThrow(new ABCBankingException());
        customerManagementService.saveCustomer(customer);
    }

}
