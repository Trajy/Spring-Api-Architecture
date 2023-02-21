package br.com.trajy.architecture.restful.exception;

import static java.lang.String.valueOf;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import br.com.trajy.architecture.restful.exception.data.struct.ErrorMessage;
import br.com.trajy.architecture.restful.exception.data.struct.detail.ErrorDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

public interface GeneralException {

    @ExceptionHandler(NullPointerException.class)
    default ResponseEntity<ErrorMessage> nullPointerExeptionHandler(NullPointerException exception,
                                                                   HttpServletRequest request) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
                .body(ErrorMessage.builder()
                        .status(valueOf(UNPROCESSABLE_ENTITY.value()))
                        .title(defaultIfEmpty(exception.getMessage(), "NullPointerException"))
                        .type(request.getRequestURI())
                        .detail(ErrorDetail.builder()
                                .error(
                                        stream(exception.getStackTrace()).limit(2)
                                                .map(StackTraceElement::toString).collect(joining(" at "))
                                )
                                .build()
                        )
                        .build()
                );
    }

}
