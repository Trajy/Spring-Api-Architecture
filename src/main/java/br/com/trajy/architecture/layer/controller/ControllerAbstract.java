package br.com.trajy.architecture.layer.controller;

import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;

public abstract class ControllerAbstract<ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>>
        implements FindByIdController<ID_TYPE, RESOURCE>, SaveController<ID_TYPE, RESOURCE>,
        UpdateController<ID_TYPE, RESOURCE>, DeleteController<ID_TYPE> {

}
