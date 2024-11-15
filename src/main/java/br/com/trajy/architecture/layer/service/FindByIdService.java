package br.com.trajy.architecture.layer.service;

import static java.lang.String.format;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FindByIdService<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    default ENTITY findById(ID_TYPE id) {
        this.beforeFind(id);
        ENTITY entity = this.getRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException(format("Entity no found for id: %f", id)));
        this.afterFind(id, entity);
        return entity;
    }

    default void beforeFind(ID_TYPE id) { }

    default void afterFind(ID_TYPE id, ENTITY entity) { }

}
