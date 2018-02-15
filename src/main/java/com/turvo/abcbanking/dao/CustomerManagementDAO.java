package com.turvo.abcbanking.dao;

import com.turvo.abcbanking.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerManagementDAO extends JpaRepository<CustomerDetails, String> {
}
