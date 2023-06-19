package br.com.trajy.architecture.layer.controller;

import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.PATH_URL_ID_REQUIRED;
import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.getMessage;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;

import br.com.trajy.architecture.layer.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface UpdateController<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>> {

    Logger log = getLogger(UpdateController.class);

    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @SchemaMapping
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    default ResponseEntity<Void> update(@Valid @RequestBody RESOURCE resource, @PathVariable ID_TYPE id, HttpServletRequest request) {
        log.info("PUT | Iniciado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        beforeUpdate(resource, request);
        AuditableEntity<Object> entity = (AuditableEntity) getConfig().getAssembly()
                .toEntity((AuditableResource<Object>) checkNotNull(resource, getMessage((PATH_URL_ID_REQUIRED))));
        setUpdateAuditData(entity);
        getConfig().getService().update(entity);
        afterUpdate(resource, request);
        log.info("PUT | Finalizado | Controller: {}", this.getClass().getSimpleName());
        return noContent().build();
    }

    default void beforeUpdate(RESOURCE resource, HttpServletRequest request) { }

    default void afterUpdate(RESOURCE resource, HttpServletRequest request) { }

    private <ID_TYPE> void setUpdateAuditData(AuditableEntity<ID_TYPE> entity) {
        entity.setModifiedBy("implementar Obtencao de Loguin");
        entity.setModifiedAt(new DateTime());
    }

}
