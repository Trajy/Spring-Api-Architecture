package br.com.trajy.architecture.layer.service;

import br.com.trajy.architecture.layer.data.struct.common.Identity;

public abstract class ServiceAbstract<ID_TYPE, ENTITY extends Identity<ID_TYPE>>
        implements FindByIdService<ID_TYPE, ENTITY>, FindByFilterService<ID_TYPE, ENTITY>,
        SaveService<ID_TYPE, ENTITY>, UpdateService<ID_TYPE, ENTITY>, DeleteService<ID_TYPE, ENTITY> {

}
