package br.com.trajy.architecture.service;

import br.com.trajy.architecture.model.AuditableEntity;
import br.com.trajy.architecture.repository.RepositoryInterface;
import java.util.List;
import java.util.Optional;

public abstract class ServiceAbstract<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>>
        implements ServiceInterface<ID_TYPE, ENTITY> {

    protected abstract RepositoryInterface<ID_TYPE, ENTITY> getRepository();

    @Override
    public ENTITY findById(ID_TYPE id) {
        beforeFind(id);
        ENTITY entity = getRepository().findById(id).orElse(null);
        afterFind(id, entity);
        return entity;
    }

    @Override
    public List<ENTITY> findByFilter() {
        beforeFindByFilter();
        // TODO - implementar na repository;
        getRepository();
        afterFindByFilter();
        return null;
    }

    @Override
    public ENTITY save(ENTITY entity) {
        beforeSave(entity);
        entity = getRepository().save(entity);
        afterSave(entity);
        return entity;
    }

    @Override
    public ENTITY update(ENTITY entity) {
        beforeUpdate(entity);
        entity = getRepository().save(entity);
        afterUpdate(entity);
        return entity;
    }

    @Override
    public void delete(ENTITY entity) {
        beforeDelete(entity);
        getRepository().delete(entity);
        afterDelete();
    }

    protected void beforeFind(ID_TYPE id) { }

    protected void afterFind(ID_TYPE id, ENTITY entity) { }

    protected void beforeFindByFilter() { }

    protected void afterFindByFilter() { }

    protected void beforeSave(ENTITY entity) { }

    protected void afterSave(ENTITY entity) { }

    protected void beforeUpdate(ENTITY entity) { }

    protected void afterUpdate(ENTITY entity)  { }

    protected void beforeDelete(ENTITY entity) { }

    protected void afterDelete() { }

}
