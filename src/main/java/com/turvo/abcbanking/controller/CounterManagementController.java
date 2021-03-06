package com.turvo.abcbanking.controller;

import com.turvo.abcbanking.model.Branch;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Operator;
import com.turvo.abcbanking.model.Result;
import com.turvo.abcbanking.service.BranchManagementService;
import com.turvo.abcbanking.service.CounterManagementService;
import com.turvo.abcbanking.service.OperatorManagementService;
import com.turvo.abcbanking.util.BuildResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Counter Management Service to add counter/operator and get counters/operators
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
@RestController
@RequestMapping("/api/counter")
public class CounterManagementController {

    private final static Logger LOG = LoggerFactory.getLogger(CounterManagementController.class);

    @Autowired
    private CounterManagementService counterManagementService;

    @Autowired
    private OperatorManagementService operatorManagementService;

    @Autowired
    private BranchManagementService branchManagementService;

    /**
     * Issues Token based on customer details supplied
     * @param counter
     * @return
     */
    @PostMapping("/addCounter")
    public Result issueToken(@Valid @RequestBody Counter counter) {
        LOG.info("Inside issueToken method");
        return BuildResponse.buildSuccessResponse(counterManagementService.addCounter(counter));
    }

    /**
     * Returns list of counters available to service
     * @return
     */
    @GetMapping("/counters")
    public Result getCounters() {
        LOG.info("Inside getCounters method");
        return BuildResponse.buildSuccessResponse(counterManagementService.getCounters());
    }

    /**
     * Operator can operate counter by supplying counter id
     * @param counterId
     */
    @GetMapping("/operate/{id}")
    public void operate(@PathVariable("id") int counterId) {
        LOG.info("Inside operate method");
        counterManagementService.operateCounter(counterId);
    }

    /**
     * Add more Operators
     * @param operator
     * @return
     */
    @PostMapping("/addOperator")
    public Result addOperator(@Valid @RequestBody Operator operator) {
        LOG.info("Inside Add Operator method");
        return BuildResponse.buildSuccessResponse(operatorManagementService.addOperator(operator));
    }

    /**
     * Returns list of operators available to serve customers
     * @return
     */
    @GetMapping("/operators")
    public Result getOperators() {
        LOG.info("Inside Get Operators method");
        return BuildResponse.buildSuccessResponse(operatorManagementService.getOperators());
    }

    /**
     * Add new branch
     * @param branch
     * @return
     */
    @PostMapping("/addBranch")
    public Result addBranch(@Valid @RequestBody Branch branch) {
        LOG.info("Inside Add Branch method");
        return BuildResponse.buildSuccessResponse(branchManagementService.addBranch(branch));
    }

    /**
     * Returls list of branches available
     * @return
     */
    @GetMapping("/branches")
    public Result getBranches() {
        LOG.info("Inside Get Branches method");
        return BuildResponse.buildSuccessResponse(branchManagementService.getBranches());
    }
}
