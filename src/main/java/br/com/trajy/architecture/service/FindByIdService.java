package br.com.trajy.architecture.service;

import br.com.trajy.architecture.data.struct.model.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FindByIdService<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default ENTITY findById(ID_TYPE id) {
        beforeFind(id);
        ENTITY entity = getRepository().findById(id).orElse(null);
        afterFind(id, entity);
        return entity;
    }

    @Transactional(rollbackFor = Exception.class)
    default void beforeFind(ID_TYPE id) { }

    @Transactional(rollbackFor = Exception.class)
    default void afterFind(ID_TYPE id, ENTITY entity) { }

}
