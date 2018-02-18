package com.turvo.abcbanking.service;

import com.turvo.abcbanking.model.Counter;

import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
public interface CounterManagementService {
    Counter addCounter(Counter counter);

    List<Counter> getCounters();

    void operateCounter(int counterId);
}
