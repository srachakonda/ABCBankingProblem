package com.turvo.abcbanking.util;

import com.turvo.abcbanking.model.Result;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 20-Feb-2018
 */
public class BuildResponse {

    public static Result buildErrorResponse(String message) {
        return new Result(Constants.ERROR, message, null);
    }

    public static Result buildSuccessResponse(Object data) {
        return new Result(Constants.SUCCESS, null, data);
    }
}
