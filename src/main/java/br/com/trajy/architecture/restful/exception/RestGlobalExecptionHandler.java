package br.com.trajy.architecture.restful.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Global exception handler with responses according RFC7807 standard
 * @Author Trajy
 */

@ControllerAdvice
public class RestGlobalExecptionHandler extends RequestBodyInterceptor implements GeneralException,
    ViolationsInterceptor {
    
}
