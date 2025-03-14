package br.com.trajy.architecture.restful.exception.utils;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.slf4j.LoggerFactory.getLogger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import java.util.Objects;

public final class ExceptionHandlerUtils {

    private static final Logger log = getLogger(ExceptionHandlerUtils.class);

    private ExceptionHandlerUtils() { }

    public static <E extends Exception> void logException(E exception) {
        String stackTrace = stream(exception.getStackTrace()).limit(50)
                .map(StackTraceElement::toString).collect(joining(" at "));
        log.error(
                exception.getClass().getSimpleName()
                        .concat(":")
                        .concat(SPACE)
                        .concat(exception.getMessage())
                        .concat(lineSeparator())
                        .concat(stackTrace)
        );
    }

    public <T, I> String generateEntityNotFoundMessage(Class<T> clazz, String fieldName, I propertyValue) {
        return format("%s not found for %s: %s", clazz.getSimpleName(), fieldName, propertyValue);
    }
    
}
