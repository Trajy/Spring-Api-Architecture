package br.com.trajy.architecture.restful.exception;

import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.ENTITY_CONSTRAINT_VIOLATION;
import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.getMessage;
import static com.google.common.collect.Iterables.getFirst;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

import br.com.trajy.architecture.restful.exception.data.struct.ErrorMessage;
import br.com.trajy.architecture.restful.exception.data.struct.detail.FieldErrorMessage;
import br.com.trajy.architecture.restful.exception.data.struct.detail.ViolationErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

public interface PrePersistenceHibernateValidatorHandle {

    @ExceptionHandler(ConstraintViolationException.class)
    default ResponseEntity<ErrorMessage<ViolationErrorMessage>> handleConstraintViolation(ConstraintViolationException exception,
                                                                      HttpServletRequest request) {
        return unprocessableEntity().body(ErrorMessage.<ViolationErrorMessage>builder()
                        .status(valueOf(UNPROCESSABLE_ENTITY.value()))
                        .title(getMessage(ENTITY_CONSTRAINT_VIOLATION))
                        .type(request.getRequestURI())
                        .detail(ViolationErrorMessage.builder()
                                .clazz(getFirst(exception.getConstraintViolations(), null)
                                        .getRootBeanClass().getSimpleName()
                                )
                                .fields(exception.getConstraintViolations().stream()
                                        .map(error -> FieldErrorMessage.builder()
                                                .field(error.getPropertyPath().toString())
                                                .validation(error.getMessage())
                                                .value(error.getInvalidValue())
                                                .build()
                                        ).collect(toList())
                                ).build()
                        ).build()
                );
    }

}
