package br.com.trajy.architecture.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "${spring.application.name}"
    )
)
public class OpenApiCoreConfig {

    @Value("${spring.application.name}")
    public String applicationName;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(
                    new Info()
                            .title(applicationName)
                );
    }

}
