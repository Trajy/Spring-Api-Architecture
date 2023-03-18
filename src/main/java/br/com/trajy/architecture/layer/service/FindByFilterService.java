package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface FindByFilterService<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default List<ENTITY> findByFilter() {
        beforeFindByFilter();
        // TODO - implementar na repository;
        getRepository();
        afterFindByFilter();
        return null;
    }

    default void beforeFindByFilter() { }

    default void afterFindByFilter() { }

}
