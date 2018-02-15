package com.turvo.abcbanking.model;

import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated
    @Column
    private ServicesOffered servicesOffered;

    @OneToMany(mappedBy = "counter", cascade = CascadeType.ALL)
    private List<Token> tokens;

    @Column
    private AccountType priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServicesOffered getServicesOffered() {
        return servicesOffered;
    }

    public void setServicesOffered(ServicesOffered servicesOffered) {
        this.servicesOffered = servicesOffered;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public AccountType getPriority() {
        return priority;
    }

    public void setPriority(AccountType priority) {
        this.priority = priority;
    }
}
