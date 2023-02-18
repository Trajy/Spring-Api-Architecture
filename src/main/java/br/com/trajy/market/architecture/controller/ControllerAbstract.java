package br.com.trajy.market.architecture.controller;

import br.com.trajy.market.architecture.model.resource.AuditableResource;

public abstract class ControllerAbstract<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>>
        implements FindController<ID_TYPE, RESOURCE>, SaveController<ID_TYPE, RESOURCE>,
        UpdateController<ID_TYPE, RESOURCE>, DeleteController<ID_TYPE> {

}
