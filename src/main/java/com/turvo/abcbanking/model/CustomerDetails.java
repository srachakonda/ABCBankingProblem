package com.turvo.abcbanking.model;

import com.turvo.abcbanking.enums.AccountType;
import com.turvo.abcbanking.enums.ServicesOffered;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class CustomerDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    @Enumerated
    private AccountType accountType;

    @ElementCollection
    @Column
    private List<ServicesOffered> servicesOpted;

/*    @Transient
    @Column
    private List<ServicesOffered> servicesOpted;*/

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ServicesOffered> getServicesOpted() {
        return servicesOpted;
    }

    public void setServicesOpted(List<ServicesOffered> servicesOpted) {
        this.servicesOpted = servicesOpted;
    }
}
