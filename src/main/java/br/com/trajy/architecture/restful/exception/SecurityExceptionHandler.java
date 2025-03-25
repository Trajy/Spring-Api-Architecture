package br.com.trajy.architecture.restful.exception;

import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.BAD_CREDENTIALS;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.status;

import br.com.trajy.architecture.restful.exception.data.struct.ErrorMessage;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

public interface SecurityExceptionHandler {

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    default ResponseEntity<ErrorMessage<String>> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException exception,
                                                                                              HttpServletRequest request) {
        return status(UNAUTHORIZED).body(ErrorMessage.<String>builder()
                .status(String.valueOf(UNAUTHORIZED.value()))
                .type(request.getRequestURI())
                .detail(BAD_CREDENTIALS.getMessage())
                .build()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    default ResponseEntity<ErrorMessage<String>> handleBadCredentialsException(BadCredentialsException exception,
                                                                               HttpServletRequest request) {
        return status(UNAUTHORIZED).body(ErrorMessage.<String>builder()
                .status(String.valueOf(UNAUTHORIZED.value()))
                .type(request.getRequestURI())
                .detail(BAD_CREDENTIALS.getMessage())
                .build()
        );
    }

}
