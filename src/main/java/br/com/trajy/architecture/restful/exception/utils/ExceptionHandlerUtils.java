package br.com.trajy.architecture.restful.exception.utils;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

public final class ExceptionHandlerUtils {

    private static final Logger log = getLogger(ExceptionHandlerUtils.class);

    private ExceptionHandlerUtils() { }

    public static void logException(Exception exception) {
        String stackTrace = stream(exception.getStackTrace()).limit(30)
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


}
