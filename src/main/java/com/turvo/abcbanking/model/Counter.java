package com.turvo.abcbanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    @Enumerated
    @Column
    private List<ServicesOffered> servicesOffered;

    @OneToMany(mappedBy = "counter",cascade = CascadeType.ALL)
    @Column
    private List<Token> tokens;

    @Column
    private AccountType accountType;
    @JsonIgnore
    public int getRank() {
        if (CollectionUtils.isEmpty(tokens)) {
            return 0;
        }
        return tokens.size();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ServicesOffered> getServicesOffered() {
        return servicesOffered;
    }

    public void setServicesOffered(List<ServicesOffered> servicesOffered) {
        this.servicesOffered = servicesOffered;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
