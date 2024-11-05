package br.com.trajy.architecture.layer.controller;

import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.PATH_URL_ID_REQUIRED;
import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.getMessage;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import br.com.trajy.architecture.layer.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;

public interface FindController<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>> {

    Logger log = getLogger(FindController.class);
    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @SchemaMapping
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<RESOURCE> find(@PathVariable ID_TYPE id, HttpServletRequest request) {
        log.info("GET | Iniciado | Controller: {}", this.getClass().getSimpleName());
        this.beforeFind(id, request);
        AuditableEntity<Object> entity = getConfig().getService().findById(checkNotNull(id, getMessage(PATH_URL_ID_REQUIRED)));
        RESOURCE resource = (RESOURCE) getConfig().getAssembly().toResource(entity);
        this.afterFind(resource, request);
        log.info("GET | Finalizado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        return ok().body(resource);
    }

    default void beforeFind(ID_TYPE id, HttpServletRequest request) { }

    default void afterFind(RESOURCE resource, HttpServletRequest request) { }
}
