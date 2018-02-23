package com.turvo.abcbanking.controller;

import com.turvo.abcbanking.model.Customer;
import com.turvo.abcbanking.model.Result;
import com.turvo.abcbanking.service.TokenManagementService;
import com.turvo.abcbanking.util.BuildResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST API to issue token
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
     * IssueToken method is used to generate token and assign it to counter
     * @param customer
     * @return
     */
    @PostMapping("/issuetoken")
    public Result issueToken(@Valid @RequestBody Customer customer) {
        LOG.info("In Issue Token method");
        return BuildResponse.buildSuccessResponse(tokenManagementService.generateToken(customer));
    }
}
