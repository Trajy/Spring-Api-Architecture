package br.com.trajy.architecture.restful.exception.util;

import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import br.com.trajy.architecture.restful.exception.type.ConstraintViolationException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public final class ValidationUtil {

    private static final Validator VALIDATOR;

    static {
        VALIDATOR = buildDefaultValidatorFactory().getValidator();
    }

    private ValidationUtil() { }

    public static <T> void checkViolations(T object) {
        Set<ConstraintViolation<Object>> violations = validate(object);
        if(isNotEmpty(violations)) {
            throw ConstraintViolationException.builder().clazz(object.getClass()).constraintViolations(violations)
                    .build();
        }
    }

    private static Set<ConstraintViolation<Object>> validate(Object body) {
        return VALIDATOR.validate(body);
    }

}
