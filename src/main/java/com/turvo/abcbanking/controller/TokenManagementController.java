package com.turvo.abcbanking.controller;

import com.turvo.abcbanking.model.CustomerDetails;
import com.turvo.abcbanking.model.Token;
import com.turvo.abcbanking.service.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/counter")
public class TokenManagementController {

    private final static Logger LOG = LoggerFactory.getLogger(TokenManagementController.class);

    @Autowired
    TokenManagementService tokenManagementService;

    @PostMapping("/issuetoken")
    public Token issueToken(@Valid @RequestBody CustomerDetails customerDetails) {
        LOG.info("In Issue Token method");
        return tokenManagementService.generateToken(customerDetails);
    }

    @GetMapping("/status")
    public List<Token> tokenCounterStatuses(){

        return null;
    }

}
