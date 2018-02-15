package com.turvo.abcbanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;
import com.turvo.abcbanking.enums.TokenStatus;

import javax.persistence.*;
import java.util.List;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated
    @Column
    private TokenStatus tokenStatus = TokenStatus.IN_PROGRESS;

    @Enumerated
    @Column
    private AccountType priority = AccountType.REGULAR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counter_id")
    @JsonIgnore
    private Counter counter;

    @ElementCollection
    private List<ServicesOffered> servicesOpted;

    public List<ServicesOffered> getServicesOpted() {
        return servicesOpted;
    }

    public void setServicesOpted(List<ServicesOffered> servicesOpted) {
        this.servicesOpted = servicesOpted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(TokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    public AccountType getPriority() {
        return priority;
    }

    public void setPriority(AccountType priority) {
        this.priority = priority;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }
}
