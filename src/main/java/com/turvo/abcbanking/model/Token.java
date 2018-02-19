package com.turvo.abcbanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.TokenStatus;

import javax.persistence.*;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
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

    @Column
    private String comments;

    @Column
    private int customerId;

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
