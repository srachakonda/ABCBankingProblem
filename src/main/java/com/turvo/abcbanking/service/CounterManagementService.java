package com.turvo.abcbanking.service;

import com.turvo.abcbanking.model.Counter;

import java.util.List;

/**
 * To operate on counter related services
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface CounterManagementService {

    /**
     * To add new counter
     * @param counter
     * @return
     */
    Counter addCounter(Counter counter);

    /**
     * Lists down available counters
     * @return
     */
    List<Counter> getCounters();

    /**
     * To Operate on particular counter
     * @param counterId
     */
    void operateCounter(long counterId);
}
