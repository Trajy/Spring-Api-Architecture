package br.com.trajy.architecture.restful.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler with responses according RFC7807 standard
 * @Author Trajy
 */
@ControllerAdvice
public class RestGlobalExecptionHandler extends ResponseEntityExceptionHandler implements GeneralException {
    
}
