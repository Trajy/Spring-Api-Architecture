package br.com.trajy.architecture.layer.controller;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import br.com.trajy.architecture.layer.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

public interface FindController<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>> {

    Logger log = getLogger(FindController.class);
    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<RESOURCE> find(ID_TYPE id, HttpServletRequest request) {
        log.info("GET | Iniciado | Controller: {}", this.getClass().getSimpleName());
        beforeFind(request);
        AuditableEntity<Object> entity = getConfig().getService().findById(id);
        RESOURCE resource = (RESOURCE) getConfig().getAssembly().toResource(entity);
        getConfig().getService().save(entity);
        afterFind(resource, request);
        log.info("GET | Finalizado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        return ok().body(resource);
    }

    default void beforeFind(HttpServletRequest request) { }

    default void afterFind(RESOURCE resource, HttpServletRequest request) { }
}
