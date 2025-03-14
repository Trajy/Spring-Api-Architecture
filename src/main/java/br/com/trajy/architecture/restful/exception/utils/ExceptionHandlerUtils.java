package br.com.trajy.architecture.restful.exception.utils;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

public final class ExceptionHandlerUtils {

    private static final Logger log = getLogger(ExceptionHandlerUtils.class);

    private ExceptionHandlerUtils() { }

    public static <E extends Exception> void logException(E exception) {
        String stackTrace = stream(exception.getStackTrace()).limit(50)
                .map(StackTraceElement::toString).collect(joining(" at "));
        log.error(format(
                    "%s: %s%s%s",
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    lineSeparator(),
                    stackTrace
                )
        );
    }

    public static  <T, I> String generateEntityNotFoundMessage(Class<T> clazz, String fieldName, I propertyValue) {
        return format("%s not found for %s: %s", clazz.getSimpleName(), fieldName, propertyValue);
    }

}
