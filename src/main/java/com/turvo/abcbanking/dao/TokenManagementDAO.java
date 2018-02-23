package com.turvo.abcbanking.dao;

import com.turvo.abcbanking.model.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface TokenManagementDAO extends CrudRepository<Token, Long> {

    /**
     * Returns all tokens based on customer id
     * @param customerId
     * @return
     */
    @Query(value = "SELECT * from token WHERE customer_id = :customerId", nativeQuery = true)
    public List<Token> findByCustomerId(@Param("customerId")Long customerId);
}
