package br.com.trajy.architecture.layer.assembly;

import java.util.List;

public interface AssemblyMapperInterface<RESOURCE, ENTITY> {

    RESOURCE toResource(ENTITY entity);
    ENTITY toEntity(RESOURCE resource);

     List<RESOURCE> toResource(List<ENTITY> entities);

    List<ENTITY> toEntity(List<RESOURCE> resources);

}
