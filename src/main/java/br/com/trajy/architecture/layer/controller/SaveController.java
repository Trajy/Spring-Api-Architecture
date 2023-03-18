package br.com.trajy.architecture.layer.controller;

import static java.lang.String.valueOf;
import static java.net.URI.create;
import static org.apache.commons.lang3.StringUtils.appendIfMissing;
import static org.joda.time.DateTime.now;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

import br.com.trajy.architecture.layer.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface SaveController<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>> {

    Logger log = getLogger(SaveController.class);
    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    default ResponseEntity<Void> save(@Valid @RequestBody RESOURCE resource, HttpServletRequest request) {
        log.info("POST | Iniciado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        beforeSave(resource, request);
        AuditableEntity entity = (AuditableEntity) getConfig().getAssembly().toEntity(resource);
        setCreateAuditData(entity);
        entity = getConfig().getService().save(entity);
        afterSave(resource, request);
        afterSave(entity, request);
        log.info("POST | Finalizado | Controller: {}", this.getClass().getSimpleName());
        return created(create(appendIfMissing(request.getRequestURI(), "/").concat(valueOf(entity.getId())))).build();
    }

    default void beforeSave(RESOURCE resource, HttpServletRequest request) { }

    default void afterSave(RESOURCE resource, HttpServletRequest request) { }

    default void afterSave(AuditableEntity<ID_TYPE> entity, HttpServletRequest request) { }

    private void setCreateAuditData(AuditableEntity<ID_TYPE> entity) {
        entity.setCreatedBy("implementar Obtencao de Loguin");
        entity.setCreatedAt(now());
    }

}
