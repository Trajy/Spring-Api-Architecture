package br.com.trajy.architecture.config;

import static com.google.common.base.Preconditions.checkNotNull;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class ApplicationContextStatic {

    private final ApplicationContext instanceContext;

    private static ApplicationContext staticContext;

    public static ApplicationContext obtainContext() {
        return checkNotNull(staticContext, "Static variable with application context is null, please add ApplicationContextStatic.class in @Import annotation or other configuration method.");
    }

    @PostConstruct
    public void setInscanceIntoStaticVariable() {
        staticContext = instanceContext;
    }

}
