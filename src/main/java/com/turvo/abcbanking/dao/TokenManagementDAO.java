package com.turvo.abcbanking.dao;

import com.turvo.abcbanking.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenManagementDAO extends JpaRepository<Token, String> {
}
