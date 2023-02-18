package br.com.trajy.architecture.controller;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.noContent;

import br.com.trajy.architecture.controller.config.ControllerConfigAbstract;
import br.com.trajy.architecture.model.AuditableEntity;
import org.slf4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeleteController<ID_TYPE> {

    Logger log = getLogger(DeleteController.class);

    <CONFIG extends ControllerConfigAbstract> CONFIG getConfig();

    @DeleteMapping(value = "/{id}")
    default ResponseEntity<Void> delete(@PathVariable ID_TYPE id, HttpRequest request) {
        log.info("DELETE | Iniciado | Controller: {}", this.getClass().getSimpleName());
        beforeDelete(request);
        AuditableEntity<Object> entity = getConfig().getService().findById(id);
        getConfig().getService().delete(entity);
        afterDelete(request);
        log.info("DELETE | Finalizado | Controller: {}", this.getClass().getSimpleName());
        return noContent().build();
    }

    default void beforeDelete(HttpRequest request) { }

    default void afterDelete(HttpRequest request) { }

}
