package br.com.trajy.architecture.restful.exception;

import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.RESOURCE_VIOLATION_CONSTRAINT;
import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.getMessage;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

import br.com.trajy.architecture.restful.exception.data.struct.ErrorMessage;
import br.com.trajy.architecture.restful.exception.data.struct.detail.FieldErrorMessage;
import br.com.trajy.architecture.restful.exception.data.struct.detail.ViolationErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler with responses according RFC7807 standard
 * @Author Trajy
 */

@ControllerAdvice
public class RestGlobalExecptionHandler extends ResponseEntityExceptionHandler implements GeneralException,
        JpaExceptionHandler, SecurityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return unprocessableEntity().body(ErrorMessage.<ViolationErrorMessage>builder()
                        .status(valueOf(UNPROCESSABLE_ENTITY.value()))
                        .title(getMessage(RESOURCE_VIOLATION_CONSTRAINT))
                        .type(request.getContextPath())
                        .detail(ViolationErrorMessage.builder()
                                .clazz(exception.getTarget().getClass().getSimpleName())
                                .fields(exception.getFieldErrors().stream()
                                        .map(error -> FieldErrorMessage.builder()
                                                .field(error.getField())
                                                .validation(error.getDefaultMessage())
                                                .value(error.getRejectedValue())
                                                .build()
                                        ).collect(toList())
                                ).build()
                        ).build()
                );
    }

}
