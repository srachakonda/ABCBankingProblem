package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.CustomerManagementDAO;
import com.turvo.abcbanking.model.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {
    @Autowired
    CustomerManagementDAO customerManagementDAO;

    /**
     *
     * @param customerDetails
     * @return
     */
    @Override
    public boolean saveCustomer(CustomerDetails customerDetails) {
        customerManagementDAO.save(customerDetails);
        return true;
    }
}
