package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.BranchManagementDAO;
import com.turvo.abcbanking.model.Branch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchManagementServiceImpl implements BranchManagementService {

    private final static Logger LOG = LoggerFactory.getLogger(BranchManagementServiceImpl.class);

    @Autowired
    private BranchManagementDAO branchManagementDAO;

    @Override
    public Branch addBranch(Branch branch) {
        LOG.info("Inside add branch method");
        return branchManagementDAO.save(branch);
    }

    @Override
    public List<Branch> getBranches() {
        LOG.info("Inside get branches method");
        return branchManagementDAO.findAll();
    }
}
