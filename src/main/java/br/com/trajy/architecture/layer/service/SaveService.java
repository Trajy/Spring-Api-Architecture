package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SaveService<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default ENTITY save(ENTITY entity) {
        beforeSave(entity);
        entity = getRepository().save(entity);
        afterSave(entity);
        return entity;
    }

    @Transactional(rollbackFor = Exception.class)
    default void beforeSave(ENTITY entity) { }

    @Transactional(rollbackFor = Exception.class)
    default void afterSave(ENTITY entity) { }

}
