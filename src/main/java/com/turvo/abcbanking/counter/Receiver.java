package com.turvo.abcbanking.counter;


import com.turvo.abcbanking.model.Token;

public interface Receiver {

    void receiveToken(Token token) throws Exception;

}