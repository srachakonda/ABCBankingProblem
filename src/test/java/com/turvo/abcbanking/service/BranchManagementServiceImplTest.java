package com.turvo.abcbanking.service;

import com.turvo.abcbanking.ABCBankingApplication;
import com.turvo.abcbanking.dao.BranchManagementDAO;
import com.turvo.abcbanking.model.Branch;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ABCBankingApplication.class)
public class BranchManagementServiceImplTest {

    @Autowired
    BranchManagementService branchManagementService;

    @Autowired
    BranchManagementDAO branchManagementDAO;

    @Test
    public void testAddBranch(){
        branchManagementService.addBranch(new Branch());

    }

}
