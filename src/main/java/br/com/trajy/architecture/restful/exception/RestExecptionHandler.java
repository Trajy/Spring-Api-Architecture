package br.com.trajy.architecture.restful.exception;

import static java.lang.String.valueOf;

import br.com.trajy.architecture.restful.exception.data.struct.ErrorMessage;
import br.com.trajy.architecture.restful.exception.data.struct.detail.ErrorDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RestExecptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorMessage> nullPointerExeptionHandler(NullPointerException exception,
                                               HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.status(response.getStatus())
                .body(ErrorMessage.builder()
                        .status(valueOf(response.getStatus()))
                        .title(exception.getMessage())
                        .type(request.getRequestURI())
                        .detail(ErrorDetail.builder()
                                    .error(exception.getCause().getMessage())
                                    .build()
                        )
                        .build()
                );
    }
}
