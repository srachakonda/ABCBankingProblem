/*
 *  Any use of the Material is governed by the terms of the actual license
 *  agreement between LeanTaaS Inc. and the user.
 *  Copyright 2016 LeanTaaS Inc., Sunnyvale CA USA.
 *  All rights reserved. Any rights not expressly granted herein are
 *  reserved.
 */
package com.leantaas.sentry.server.utils;

import com.leantaas.sentry.server.model.Result;

/**
 * 
 * @author <a href="uday.dharbamulla@imaginea.com">udayd</a>
 * @version $Revision: 1.0 $, $Date: 12-Jan-2016
 */
public class BuildResponse {

	public static Result buildErrorResponse(String message) {
		return new Result(Constants.ERROR, message, null);
	}

	public static Result buildSuccessResponse(Object data) {
		return new Result(Constants.SUCCESS, null, data);
	}
}
