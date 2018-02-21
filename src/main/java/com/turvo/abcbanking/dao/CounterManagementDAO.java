package com.turvo.abcbanking.dao;

import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface CounterManagementDAO extends JpaRepository<Counter, Long> {

    List<Counter> findByAccountType(AccountType accountType);

}
