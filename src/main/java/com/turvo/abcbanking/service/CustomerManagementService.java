package com.turvo.abcbanking.service;

import com.turvo.abcbanking.model.Customer;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface CustomerManagementService {

    /**
     * Save customer details in DB
     * @param customerDetails
     * @return
     */
    public boolean saveCustomer(Customer customerDetails);
}
