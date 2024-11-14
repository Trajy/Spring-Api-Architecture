package br.com.trajy.architecture.layer.controller.config;

import br.com.trajy.architecture.layer.assembly.AssemblyMapperInterface;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.data.struct.resource.AuditableResource;
import br.com.trajy.architecture.layer.service.ServiceAbstract;

public interface ControllerConfigInterface {

    <ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>,
            SERVICE extends ServiceAbstract<ID_TYPE, ENTITY>> SERVICE getService();

    <ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>, ENTITY extends AuditableEntity<ID_TYPE>,
            ASSEMBLY extends AssemblyMapperInterface<RESOURCE, ENTITY>> ASSEMBLY getAssembly();

}
