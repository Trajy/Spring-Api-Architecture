package br.com.trajy.market.architecture.service;

import br.com.trajy.market.architecture.model.AuditableEntity;
import br.com.trajy.market.architecture.repository.RepositoryInterface;

public abstract class ServiceAbstract<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>>
        implements ServiceInterface<ID_TYPE, ENTITY> {

    protected abstract RepositoryInterface<ID_TYPE, ENTITY> getRepository();

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

    protected void beforeSave(ENTITY entity) { }

    protected void afterSave(ENTITY entity) { }

    protected void beforeUpdate(ENTITY entity) { }

    protected void afterUpdate(ENTITY entity)  { }

    protected void beforeDelete(ENTITY entity) { }

    protected void afterDelete() { }

}
