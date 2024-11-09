package br.com.trajy.architecture.config;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextStatic {

    private static ApplicationContext staticContext;

    @Bean
    public void getContextInstance(ApplicationContext context) {
        staticContext = context;
    }

    public static ApplicationContext obtainContext() {
        return checkNotNull(
                staticContext,
                "Static variable with application context is null, please add ApplicationContextStatic.class in @Import annotation or other configuration method."
        );
    }

}
