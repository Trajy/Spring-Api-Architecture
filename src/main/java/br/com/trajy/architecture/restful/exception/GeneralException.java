package br.com.trajy.architecture.restful.exception;

import static java.lang.String.valueOf;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.badRequest;

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
        String stackTrace = stream(exception.getStackTrace()).limit(10)
                .map(StackTraceElement::toString).collect(joining(" at "));
        log.error(stackTrace);
        return badRequest().body(ErrorMessage.builder()
                        .status(valueOf(UNPROCESSABLE_ENTITY.value()))
                        .title(INTERNAL_SERVER_ERROR.name())
                        .type(request.getRequestURI())
                        .detail(exception.getMessage())
                        .build()
                );
    }

}
