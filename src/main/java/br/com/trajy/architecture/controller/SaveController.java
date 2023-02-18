package br.com.trajy.architecture.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

import br.com.trajy.architecture.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.model.AuditableEntity;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SaveController<RESOURCE> {

    Logger log = LoggerFactory.getLogger(SaveController.class);
    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Transactional(propagation = SUPPORTS, rollbackFor = Exception.class)
    default ResponseEntity<Void> save(@RequestBody RESOURCE resource, HttpRequest request) {
        log.info("POST | Iniciado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        beforeSave(resource, request);
        AuditableEntity<Object> entity = getConfig().getAssembler().toEntity(resource);
        setAuditData(entity);
        getConfig().getService().save(entity);
        afterSave(resource, request);
        log.info("POST | Finalizado | Controller: {}", this.getClass().getSimpleName());
        return buildResponse(resource, request);
    }

    default void beforeSave(RESOURCE resource, HttpRequest request) { }

    default void afterSave(RESOURCE resource, HttpRequest request) { }

    private <ID_TYPE> void setAuditData(AuditableEntity<ID_TYPE> entity) {
        entity.setCratedBy("implementar Obtencao de Loguin");
        entity.setCreatedAt(new DateTime());
    }
    private ResponseEntity<Void> buildResponse(RESOURCE resource, HttpRequest request) {
        //TODO - make return headers
        return created(request.getURI()).build();
    }

}
