package com.turvo.abcbanking.dao;

import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterManagementDAO extends JpaRepository<Counter, Integer> {

    List<Counter> findByAccountType(AccountType accountType);

}
