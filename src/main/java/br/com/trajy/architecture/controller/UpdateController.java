package br.com.trajy.architecture.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

import br.com.trajy.architecture.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.model.AuditableEntity;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdateController<RESOURCE> {

    Logger log = LoggerFactory.getLogger(UpdateController.class);

    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @Transactional(propagation = SUPPORTS, rollbackFor = Exception.class)
    default ResponseEntity<Void> update(@PathVariable String id, @RequestBody RESOURCE resource, HttpRequest request) {
        log.info("PUT | Iniciado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        beforeUpdate(resource, request);
        AuditableEntity<Object> entity = getConfig().getAssembly().toEntity(resource);
        setAuditData(entity);
        // TODO - implementar update
        getConfig().getService();
        afterUpdate(resource, request);
        log.info("PUT | Finalizado | Controller: {}", this.getClass().getSimpleName());
        return noContent().build();
    }

    default void beforeUpdate(RESOURCE resource, HttpRequest request) { }

    default void afterUpdate(RESOURCE resource, HttpRequest request) { }

    private <ID_TYPE> void setAuditData(AuditableEntity<ID_TYPE> entity) {
        entity.setModifiedBy("implementar Obtencao de Loguin");
        entity.setModifiedAt(new DateTime());
    }

}
