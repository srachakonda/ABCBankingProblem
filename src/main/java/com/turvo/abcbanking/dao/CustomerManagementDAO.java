package com.turvo.abcbanking.dao;

import com.turvo.abcbanking.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface CustomerManagementDAO extends JpaRepository<CustomerDetails, Integer> {
}
