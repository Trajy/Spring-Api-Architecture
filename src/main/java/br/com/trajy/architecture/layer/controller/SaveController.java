package br.com.trajy.architecture.layer.controller;

import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.REQUEST_BODY_REQUERED;
import static br.com.trajy.architecture.restful.constant.ErrorMessageEnum.getMessageFromEnum;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.net.URI.create;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

import br.com.trajy.architecture.layer.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;

public interface SaveController<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>> {

    Logger log = getLogger(SaveController.class);
    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    default ResponseEntity<Void> save(@RequestBody RESOURCE resource, HttpServletRequest request) {
        log.info("POST | Iniciado | Controller: {} | Entity: {}", this.getClass().getSimpleName(), resource);
        beforeSave(resource, request);
        AuditableEntity<Object> entity = (AuditableEntity<Object>) getConfig().getAssembly()
                .toEntity(checkNotNull(resource.getCratedBy()));
        setCreateAuditData(entity);
        getConfig().getService().save(entity);
        afterSave(resource, request);
        log.info("POST | Finalizado | Controller: {}", this.getClass().getSimpleName());
        return buildResponse(resource, request);
    }

    default void beforeSave(RESOURCE resource, HttpServletRequest request) { }

    default void afterSave(RESOURCE resource, HttpServletRequest request) { }

    private <ID_TYPE> void setCreateAuditData(AuditableEntity<ID_TYPE> entity) {
        entity.setCratedBy("implementar Obtencao de Loguin");
        entity.setCreatedAt(new DateTime());
    }
    private ResponseEntity<Void> buildResponse(RESOURCE resource, HttpServletRequest request) {
        //TODO - make return headers
        return created(create(request.getRequestURI())).build();
    }

}
