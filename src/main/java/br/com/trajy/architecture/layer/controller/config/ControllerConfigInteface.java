package br.com.trajy.architecture.layer.controller.config;

import br.com.trajy.architecture.layer.assembly.AssemblyMapperInterface;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.service.ServiceAbstract;

public interface ControllerConfigInteface {

    <ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>,
            SERVICE extends ServiceAbstract<ID_TYPE, ENTITY>> SERVICE getService();

    <RESOURCE, ENTITY, ASSEMBLY extends AssemblyMapperInterface<RESOURCE, ENTITY>> ASSEMBLY getAssembly();

}
