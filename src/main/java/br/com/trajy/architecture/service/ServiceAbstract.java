package br.com.trajy.architecture.service;

import br.com.trajy.architecture.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.repository.RepositoryInterface;
import java.util.List;

public abstract class ServiceAbstract<ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>>
        implements FindByIdService<ID_TYPE, ENTITY>, FindByFilterService<ID_TYPE, ENTITY>,
        SaveService<ID_TYPE, ENTITY>, UpdateService<ID_TYPE, ENTITY>, DeleteService<ID_TYPE, ENTITY> {

}
