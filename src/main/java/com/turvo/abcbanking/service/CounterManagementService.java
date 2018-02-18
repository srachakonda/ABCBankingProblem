package com.turvo.abcbanking.service;

import com.turvo.abcbanking.model.Counter;

import java.util.List;

public interface CounterManagementService {
    public Counter addCounter(Counter counter);

    public List<Counter> getCounters();

    public void operateCounter(int counterId);
}
