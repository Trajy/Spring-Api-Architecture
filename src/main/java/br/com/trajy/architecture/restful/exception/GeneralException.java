package br.com.trajy.architecture.restful.exception;

import static br.com.trajy.architecture.restful.exception.utils.ExceptionHandlerUtils.logException;
import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

import br.com.trajy.architecture.restful.exception.data.struct.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

public interface GeneralException {

    @ExceptionHandler(IllegalArgumentException.class)
    default ResponseEntity<ErrorMessage<String>> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        logException(exception);
        return unprocessableEntity().body(ErrorMessage.<String>builder()
                .status(valueOf(UNPROCESSABLE_ENTITY.value()))
                .title(UNPROCESSABLE_ENTITY.name())
                .type(request.getRequestURI())
                .detail(exception.getMessage())
                .build()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    default ResponseEntity<ErrorMessage<String>> illegalArgumentException(IllegalStateException exception, HttpServletRequest request) {
        logException(exception);
        return unprocessableEntity().body(ErrorMessage.<String>builder()
                .status(valueOf(UNPROCESSABLE_ENTITY.value()))
                .title(UNPROCESSABLE_ENTITY.name())
                .type(request.getRequestURI())
                .detail(exception.getMessage())
                .build()
        );
    }

}
