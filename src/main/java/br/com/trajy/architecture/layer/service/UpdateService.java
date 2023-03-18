package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UpdateService<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default ENTITY update(ENTITY entity) {
        beforeUpdate(entity);
        entity = getRepository().save(entity);
        afterUpdate(entity);
        return entity;
    }

    default void beforeUpdate(ENTITY entity) { }

    default void afterUpdate(ENTITY entity)  { }

}
