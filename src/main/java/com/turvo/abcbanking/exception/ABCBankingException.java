package com.turvo.abcbanking.exception;


import com.turvo.abcbanking.util.MessageUtil;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 19-Feb-2018
 */
public class ABCBankingException extends RuntimeException {

    private static final long serialVersionUID = -3417821814368540684L;

    private String errorCode = null;

    private String message = null;

    private Object[] args = null;

    public ABCBankingException() {
        super();
    }

    public ABCBankingException(String errorCode, Object... args) {
        super(MessageUtil.getMessage(errorCode, args));
        this.errorCode = errorCode;
        this.message = MessageUtil.getMessage(errorCode, args);
        this.args = args;
    }

    public ABCBankingException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

