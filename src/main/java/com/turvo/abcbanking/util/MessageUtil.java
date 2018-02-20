/*
 *  Any use of the Material is governed by the terms of the actual license
 *  agreement between LeanTaaS Inc. and the user.
 *  Copyright 2014 LeanTaaS Inc., Sunnyvale CA USA.
 *  All rights reserved. Any rights not expressly granted herein are
 *  reserved.
 */
package com.turvo.abcbanking.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 19-Feb-2018
 */
public class MessageUtil {

	private static ReloadableResourceBundleMessageSource messageSource;
    
    static {
        messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600);
    }

    /**
     * 
     * @param message
     * @param args
     * @return
     */
    public static String getMessage(String message, Object...args){
        return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
    }
}
