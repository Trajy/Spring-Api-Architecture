package br.com.trajy.architecture.controller.config;

import br.com.trajy.architecture.assembly.AssemblyMapperAbstract;
import br.com.trajy.architecture.model.AuditableEntity;
import br.com.trajy.architecture.service.ServiceAbstract;

public interface ControllerConfigInteface {

    <ID_MODEL, ENTITY extends AuditableEntity<ID_MODEL>,
            SERVICE extends ServiceAbstract<ID_MODEL, ENTITY>> SERVICE getService();

    <ID_MODEL, RESOURCE, ENTITY extends AuditableEntity<ID_MODEL>,
            ASSEMBLY extends AssemblyMapperAbstract<RESOURCE, ENTITY>> ASSEMBLY getAssembler();

}
