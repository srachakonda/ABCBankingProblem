package com.turvo.abcbanking.dao;

import com.turvo.abcbanking.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface TokenManagementDAO extends JpaRepository<Token, Integer> {
}
