package br.com.trajy.architecture.service;

import br.com.trajy.architecture.data.struct.model.AuditableEntity;
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

    @Transactional(rollbackFor = Exception.class)
    default void beforeFindByFilter() { }

    @Transactional(rollbackFor = Exception.class)
    default void afterFindByFilter() { }

}
