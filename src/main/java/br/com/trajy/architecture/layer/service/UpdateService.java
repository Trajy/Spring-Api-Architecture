package br.com.trajy.architecture.layer.service;

import static br.com.trajy.architecture.layer.service.utils.ServiceUtils.formatUpdateErrorMessage;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.nonNull;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UpdateService<ID_TYPE, ENTITY extends Identity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default ENTITY update(ENTITY entity) {
        checkState(nonNull(entity.getId()), formatUpdateErrorMessage(entity));
        this.beforeUpdate(entity);
        entity = this.getRepository().save(entity);
        this.afterUpdate(entity);
        return entity;
    }

    default void beforeUpdate(ENTITY entity) { }

    default void afterUpdate(ENTITY entity)  { }

}
