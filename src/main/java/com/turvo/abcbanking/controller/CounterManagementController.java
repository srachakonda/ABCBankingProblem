package com.turvo.abcbanking.controller;

import com.turvo.abcbanking.model.Counter;
import com.turvo.abcbanking.service.CounterManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/counter")
public class CounterManagementController {

    private final static Logger LOG = LoggerFactory.getLogger(CounterManagementController.class);

    @Autowired
    private CounterManagementService counterManagementService;

    @PostMapping("/addCounter")
    public Counter issueToken(@Valid @RequestBody Counter counter) {
        LOG.info("Inside issueToken method");
        return counterManagementService.addCounter(counter);
    }

    @GetMapping("/counters")
    public List<Counter> getCounters(){
        LOG.info("Inside getCounters method");
        return counterManagementService.getCounters();
    }

    @GetMapping("/operate/{id}")
    public void operate(@PathVariable("id") int counterId){
        LOG.info("Inside operate method");
        counterManagementService.operateCounter(counterId);
    }

}
