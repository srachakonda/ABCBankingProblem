package com.turvo.abcbanking.controller;

import com.turvo.abcbanking.model.CustomerDetails;
import com.turvo.abcbanking.service.CustomerManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TokenManagementController {

    private final static Logger LOG = LoggerFactory.getLogger(TokenManagementController.class);

    @Autowired
    CustomerManagementService customerManagementService;

    @PostMapping("/issuetoken")
    public boolean issueToken(@Valid @RequestBody CustomerDetails customerDetails) {
        LOG.info("In Issue Token method");
        if(true){
            customerManagementService.saveCustomer(customerDetails);
        }

        return true;
    }
}
