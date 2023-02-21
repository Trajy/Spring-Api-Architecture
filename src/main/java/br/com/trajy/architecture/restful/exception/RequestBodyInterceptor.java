package br.com.trajy.architecture.restful.exception;

import static br.com.trajy.architecture.restful.exception.util.ValidationUtil.checkViolations;
import static java.util.List.of;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import javax.validation.Valid;
import java.lang.reflect.Type;
public abstract class RequestBodyInterceptor extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
   public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                               Class<? extends HttpMessageConverter<?>> converterType) {
        if(of(parameter.getParameterAnnotations()).stream()
                .anyMatch(annotation -> annotation.annotationType().equals(Valid.class))) {
            //checkViolations(body);
        }
        return body;
    }
}
