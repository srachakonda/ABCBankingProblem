package com.turvo.abcbanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 12-Feb-2018
 */
@Entity
@Table(name = "counter")
public class Counter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Enumerated
    @Column
    private List<ServicesOffered> servicesOffered;

    @OneToMany(mappedBy = "counter", cascade = CascadeType.ALL)
    @Column
    private List<Token> tokens;

    @Column
    private AccountType accountType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @JsonIgnore
    private Branch branch;

    @OneToOne(cascade = CascadeType.ALL)
    private Operator operator;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    public Counter() {
    }

    public Counter(List<ServicesOffered> servicesOffered, AccountType accountType, Branch branch, Operator operator, List<Token> tokens) {
        this.servicesOffered = servicesOffered;
        this.accountType = accountType;
        this.branch = branch;
        this.operator = operator;
        this.tokens=tokens;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
