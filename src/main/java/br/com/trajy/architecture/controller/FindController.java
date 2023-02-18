package br.com.trajy.architecture.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import br.com.trajy.architecture.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.model.AuditableEntity;
import br.com.trajy.architecture.resource.AuditableResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

public interface FindController<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>> {

    Logger log = LoggerFactory.getLogger(FindController.class);
    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Transactional(rollbackFor = Exception.class)
    default ResponseEntity<RESOURCE> find(ID_TYPE id, HttpRequest request) {
        log.info("GET | Iniciado | Controller: {}", this.getClass().getSimpleName());
        beforeFind(request);
        AuditableEntity<Object> entity = getConfig().getService().findById(id);
        RESOURCE resource = (RESOURCE) getConfig().getAssembly().toResource(entity);
        getConfig().getService().save(entity);
        afterFind(resource, request);
        log.info("GET | Finalizado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        return ok().body(resource);
    }

    default void beforeFind(HttpRequest request) { }

    default void afterFind(RESOURCE resource, HttpRequest request) { }
}
