package com.turvo.abcbanking.service;

import com.turvo.abcbanking.model.CustomerDetails;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface CustomerManagementService {

    public boolean saveCustomer(CustomerDetails customerDetails);
}
