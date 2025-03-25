package br.com.trajy.architecture.layer.controller;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.data.domain.Pageable.ofSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import br.com.trajy.architecture.layer.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.layer.data.struct.common.Identity;
import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import java.util.List;

public interface FindByFilterController<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>> {

    Logger log = getLogger(FindByFilterController.class);
    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @SchemaMapping
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    default ResponseEntity<List<RESOURCE>> find(
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int page,
            HttpServletRequest request
    ) {
        log.info("GET | Iniciado | Controller: {}", this.getClass().getSimpleName());
        this.beforeFind(request);
        List<Identity<Object>> entity = getConfig().getService().findByFilter(ofSize(pageSize).withPage(page));
        List<RESOURCE> resources = (List<RESOURCE>) getConfig().getAssembly().toResource(entity);
        this.afterFind(resources, request);
        log.info("GET | Finalizado | Controller: {} | Entity: {}", this.getClass().getSimpleName());
        return ok().body(resources);
    }

    default void beforeFind(HttpServletRequest request) { }

    default void afterFind(List<RESOURCE> resources, HttpServletRequest request) { }
}
