package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.CustomerManagementDAO;
import com.turvo.abcbanking.model.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {
    @Autowired
    CustomerManagementDAO customerManagementDAO;

    @Override
    public boolean saveCustomer(CustomerDetails customerDetails) {
        customerManagementDAO.save(customerDetails);
        return true;
    }
}
