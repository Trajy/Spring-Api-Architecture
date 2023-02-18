package br.com.trajy.market.architecture.controller.config;

import br.com.trajy.market.architecture.assembly.AssemblyMapperAbstract;
import br.com.trajy.market.architecture.model.AuditableEntity;
import br.com.trajy.market.architecture.service.ServiceAbstract;

public interface ControllerConfigInteface {

    <ID_TYPE, ENTITY extends AuditableEntity<ID_TYPE>,
            SERVICE extends ServiceAbstract<ID_TYPE, ENTITY>> SERVICE getService();

    <ID_TYPE, RESOURCE, ENTITY extends AuditableEntity<ID_TYPE>,
            ASSEMBLY extends AssemblyMapperAbstract<RESOURCE, ENTITY>> ASSEMBLY getAssembly();

}
