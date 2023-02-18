package br.com.trajy.architecture.service;

import br.com.trajy.architecture.model.AuditableEntity;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ServiceInterface<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    ENTITY save(@NotNull ENTITY entity);

    ENTITY update(@NotNull ENTITY entity);

    void delete(@NotNull ENTITY entity);

    ENTITY findById(ID_TYPE id);

    //TODO - Verificar como implementar
    List<ENTITY> findByFilter();

}
