package br.com.trajy.architecture.layer.controller.util;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public final class SecurityUtils {

    private SecurityUtils() { }

    public static String getAuthenticatedUsername() {
        return getContext().getAuthentication().getName();
    }

    public static <T> T getAuthenticatedUserId() {
        return (T) getContext().getAuthentication().getDetails();
    }

}
