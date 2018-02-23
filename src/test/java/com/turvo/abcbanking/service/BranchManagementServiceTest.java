package com.turvo.abcbanking.service;

import com.turvo.abcbanking.ABCBankingApplication;
import com.turvo.abcbanking.dao.BranchManagementDAO;
import com.turvo.abcbanking.model.Branch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ABCBankingApplication.class)
public class BranchManagementServiceTest {

    @Autowired
    private BranchManagementService branchManagementService;
    private BranchManagementDAO managementDAOmock = Mockito.mock(BranchManagementDAO.class);
    private Branch branch1 = new Branch("Jubilee Hills", 1L);

    @Test
    public void testAddBranch() {
        Mockito.when(managementDAOmock.save(branch1)).thenReturn(branch1);
        Branch resultBranch = branchManagementService.addBranch(branch1);
        Assert.assertTrue(resultBranch.getName().equals("Jubilee Hills"));
        Assert.assertTrue(resultBranch.getId().equals(1L));
    }


    @Test
    public void testGetBranches() {

        List<Branch> branches = new ArrayList<Branch>();
        Branch branch1 = new Branch("Jubilee Hills", 1L);
        Branch branch2 = new Branch("Banjara Hills", 2L);
        Branch branch3 = new Branch("Punjagutta", 3L);
        branches.add(branch1);
        branches.add(branch2);
        branches.add(branch3);
        Mockito.when(managementDAOmock.findAll()).thenReturn(branches);
        Assert.assertEquals(branches.get(0).getId(), branchManagementService.getBranches().get(0).getId());
        //we can assert with asserEquals(list1,list2) for list comparisions
    }

}
