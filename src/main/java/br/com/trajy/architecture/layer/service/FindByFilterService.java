package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface FindByFilterService<ID_TYPE, ENTITY extends Identity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    default List<ENTITY> findByFilter(Pageable pageable) {
        beforeFindByFilter();
        getRepository();
        afterFindByFilter();
        return null;
    }

    default void beforeFindByFilter() { }

    default void afterFindByFilter() { }

}
