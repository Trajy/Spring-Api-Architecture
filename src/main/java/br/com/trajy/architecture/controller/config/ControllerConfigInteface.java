package br.com.trajy.architecture.controller.config;

import br.com.trajy.architecture.assembly.AssemblyMapperInterface;
import br.com.trajy.architecture.model.AuditableEntity;
import br.com.trajy.architecture.resource.AuditableResource;
import br.com.trajy.architecture.service.ServiceAbstract;

public interface ControllerConfigInteface {

    <ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>,
            SERVICE extends ServiceAbstract<ID_TYPE, ENTITY>> SERVICE getService();

    <ID_TYPE, RESOURCE extends AuditableResource<ID_TYPE>, ENTITY extends AuditableEntity<ID_TYPE>,
            ASSEMBLY extends AssemblyMapperInterface<RESOURCE, ENTITY>> ASSEMBLY getAssembly();

}
