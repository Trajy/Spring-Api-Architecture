package br.com.trajy.architecture.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
public class ApplicationContextStaticConfig {

    @Autowired
    private ApplicationContext instanceContext;

    private static ApplicationContext staticContext;

    public static ApplicationContext obtainContext() {
        return staticContext;
    }

    @PostConstruct
    public void setInscanceIntoStaticVariable() {
        staticContext = instanceContext;
    }

}
