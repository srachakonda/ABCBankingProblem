package com.turvo.abcbanking.service;


import com.turvo.abcbanking.model.Branch;

import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 20-Feb-2018
 */
public interface BranchManagementService {
    public Branch addBranch(Branch branch);

    public List<Branch> getBranches();

}

