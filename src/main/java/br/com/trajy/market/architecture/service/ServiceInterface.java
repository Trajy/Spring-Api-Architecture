package br.com.trajy.market.architecture.service;

import br.com.trajy.market.architecture.model.AuditableEntity;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ServiceInterface<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    @Transactional(rollbackFor = Exception.class)
    ENTITY save(@NotNull ENTITY entity);

    @Transactional(rollbackFor = Exception.class)
    ENTITY update(@NotNull ENTITY entity);

    @Transactional(rollbackFor = Exception.class)
    void delete(@NotNull ENTITY entity);

    @Transactional(rollbackFor = Exception.class)
    ENTITY findById(ID_TYPE id);

    @Transactional(rollbackFor = Exception.class)
    //TODO - Verificar como implementar
    List<ENTITY> findByFilter();

}
