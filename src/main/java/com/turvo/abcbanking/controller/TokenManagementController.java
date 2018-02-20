package com.turvo.abcbanking.controller;

import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.CustomerDetails;
import com.turvo.abcbanking.model.Token;
import com.turvo.abcbanking.service.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */

@RestController
@RequestMapping("/api")
public class TokenManagementController {

    private final static Logger LOG = LoggerFactory.getLogger(TokenManagementController.class);

    @Autowired
    private TokenManagementService tokenManagementService;

    /**
     * @param customerDetails
     * @return
     */
    @PostMapping("/issuetoken")
    public Token issueToken(@Valid @RequestBody CustomerDetails customerDetails) {
        LOG.info("In Issue Token method");
        return tokenManagementService.generateToken(customerDetails);
    }

    /**
     * @return
     */
    @GetMapping("/status")
    public List<Counter> tokenCounterStatuses() {
        LOG.info("In tokenCounterStatuses method");
        return tokenManagementService.tokenStatuses();
    }
}
