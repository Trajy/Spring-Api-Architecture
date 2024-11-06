package br.com.trajy.architecture.restful.exception;

import static br.com.trajy.architecture.restful.exception.utils.ExceptionHandlerUtils.logException;
import static java.lang.String.valueOf;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

import br.com.trajy.architecture.restful.exception.data.struct.ErrorMessage;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

public interface GeneralException {

    Logger log = getLogger(GeneralException.class);

    @ExceptionHandler(NullPointerException.class)
    default ResponseEntity<ErrorMessage> nullPointerExeptionHandler(NullPointerException exception,
                                                                   HttpServletRequest request) {
        logException(exception);
        return badRequest().body(ErrorMessage.builder()
                .status(valueOf(BAD_REQUEST.value()))
                .title(INTERNAL_SERVER_ERROR.name())
                .type(request.getRequestURI())
                .detail(exception.getMessage())
                .build()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    default ResponseEntity<ErrorMessage> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        logException(exception);
        return unprocessableEntity().body(ErrorMessage.builder()
                .status(valueOf(UNPROCESSABLE_ENTITY.value()))
                .title(UNPROCESSABLE_ENTITY.name())
                .type(request.getRequestURI())
                .detail(exception.getMessage())
                .build()
        );
    }

}
