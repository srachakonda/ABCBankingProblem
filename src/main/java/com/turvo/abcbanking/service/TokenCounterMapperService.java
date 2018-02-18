package com.turvo.abcbanking.service;

import com.turvo.abcbanking.model.Token;

public interface TokenCounterMapperService {

    Token assignTokenToCounter(Token token);
}
