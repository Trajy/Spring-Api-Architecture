package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DeleteService<ID_TYPE, ENTITY extends Identity<ID_TYPE>> {

    <REPOSITORY extends JpaRepository<ENTITY, ID_TYPE>> REPOSITORY getRepository();

    @Transactional(rollbackFor = Exception.class)
    default void delete(ENTITY entity) {
        this.beforeDelete(entity);
        this.getRepository().delete(entity);
        this.afterDelete();
    }

    default void beforeDelete(ENTITY entity) { }

    default void afterDelete() { }

}
