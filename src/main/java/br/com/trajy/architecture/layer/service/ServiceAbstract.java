package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;

public abstract class ServiceAbstract<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>>
        implements FindByIdService<ID_TYPE, ENTITY>, FindByFilterService<ID_TYPE, ENTITY>,
        SaveService<ID_TYPE, ENTITY>, UpdateService<ID_TYPE, ENTITY>, DeleteService<ID_TYPE, ENTITY> {

}
