package com.turvo.abcbanking.controller;

import com.turvo.abcbanking.model.Branch;
import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.model.Operator;
import com.turvo.abcbanking.service.BranchManagementService;
import com.turvo.abcbanking.service.CounterManagementService;
import com.turvo.abcbanking.service.OperatorManagementService;
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
     * @param counter
     * @return
     */
    @PostMapping("/addCounter")
    public Counter issueToken(@Valid @RequestBody Counter counter) {
        LOG.info("Inside issueToken method");
        return counterManagementService.addCounter(counter);
    }

    /**
     * @return
     */
    @GetMapping("/counters")
    public List<Counter> getCounters() {
        LOG.info("Inside getCounters method");
        return counterManagementService.getCounters();
    }

    /**
     * @param counterId
     */
    @GetMapping("/operate/{id}")
    public void operate(@PathVariable("id") int counterId) {
        LOG.info("Inside operate method");
        counterManagementService.operateCounter(counterId);
    }

    /**
     * @param operator
     * @return
     */
    @PostMapping("/addOperator")
    public Operator addOperator(@Valid @RequestBody Operator operator) {
        LOG.info("Inside Add Operator method");
        return operatorManagementService.addOperator(operator);
    }

    @PostMapping("/addBranch")
    public Branch addBranch(@Valid @RequestBody Branch branch) {
        LOG.info("Inside Add Branch method");
        return branchManagementService.addBranch(branch);
    }

}
