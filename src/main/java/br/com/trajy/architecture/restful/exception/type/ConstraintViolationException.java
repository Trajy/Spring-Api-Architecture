package br.com.trajy.architecture.restful.exception.type;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
@Setter
@Builder
public class ConstraintViolationException extends RuntimeException {

    Class clazz;

    Set<ConstraintViolation<Object>> constraintViolations;

}
