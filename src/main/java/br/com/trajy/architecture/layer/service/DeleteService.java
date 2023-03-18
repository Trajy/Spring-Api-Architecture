package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DeleteService<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default void delete(ENTITY entity) {
        beforeDelete(entity);
        getRepository().delete(entity);
        afterDelete();
    }

    default void beforeDelete(ENTITY entity) { }

    default void afterDelete() { }

}
