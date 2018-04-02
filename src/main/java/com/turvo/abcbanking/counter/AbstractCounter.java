package com.turvo.abcbanking.counter;

import com.turvo.abcbanking.dao.TokenManagementDAO;
import com.turvo.abcbanking.enums.TokenStatus;
import com.turvo.abcbanking.model.Token;
import com.turvo.abcbanking.service.TokenManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 */
public class AbstractCounter {

    private final static Logger LOG = LoggerFactory.getLogger(AbstractCounter.class);

    public int counterId;
    public String counterName;
    public String counterType;
    @Autowired
    public TokenManagementService tokenService;

    @Autowired
    public TokenManagementDAO tokenManagementDAO;

    public void updateTokenComments(Token tokenDTO, String comments) {
        LOG.info("Inside update token comments method");
        tokenDTO.setComments(tokenDTO.getComments() + " " + comments);
        tokenManagementDAO.save(tokenDTO);

    }

    public void updateTokenStatusAsInProgress(Token tokenDTO) {
        LOG.info("Inside update token status to in progress method");
        tokenDTO.setTokenStatus(TokenStatus.IN_PROGRESS);
        tokenManagementDAO.save(tokenDTO);
    }

    public void updateTokenStatusAsCompleted(Token tokenDTO) {
        LOG.info("Inside update token status to complete method");
        tokenDTO.setTokenStatus(TokenStatus.COMPLETED);
        tokenManagementDAO.save(tokenDTO);
    }

}
