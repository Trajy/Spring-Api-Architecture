package br.com.trajy.architecture.layer.service;

import static br.com.trajy.architecture.layer.service.utils.ServiceUtils.formatSaveErrorMessage;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.isNull;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.service.utils.ServiceUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SaveService<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default ENTITY save(ENTITY entity) {
        checkState(isNull(entity), formatSaveErrorMessage(entity));
        this.beforeSave(entity);
        entity = this.getRepository().save(entity);
        this.afterSave(entity);
        return entity;
    }

    default void beforeSave(ENTITY entity) { }

    default void afterSave(ENTITY entity) { }

}
