package com.turvo.abcbanking.service;

import com.turvo.abcbanking.dao.OperatorManagementDAO;
import com.turvo.abcbanking.model.Operator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 20-Feb-2018
 */
@Service
public class OperatorManagementServiceImpl implements OperatorManagementService {
    private final static Logger LOG = LoggerFactory.getLogger(OperatorManagementServiceImpl.class);

    @Autowired
    private OperatorManagementDAO operatorManagementDAO;

    @Override
    public Operator addOperator(Operator operator) {
        LOG.info("Inside Add Operator method");
        return operatorManagementDAO.save(operator);
    }

    @Override
    public List<Operator> getOperators() {
        LOG.info("Inside Get Operators method");
        return operatorManagementDAO.findAll();
    }
}
