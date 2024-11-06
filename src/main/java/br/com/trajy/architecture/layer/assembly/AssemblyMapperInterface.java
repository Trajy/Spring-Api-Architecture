package br.com.trajy.architecture.layer.assembly;

import static java.util.stream.Collectors.toList;

import java.util.List;

public interface AssemblyMapperInterface<RESOURCE, ENTITY> {

    RESOURCE toResource(ENTITY entity);

    ENTITY toEntity(RESOURCE resource);

     default List<RESOURCE> toResource(List<ENTITY> entities) {
         return entities.stream().map(this::toResource).collect(toList());
     }

    default List<ENTITY> toEntity(List<RESOURCE> resources) {
         return resources.stream().map(this::toEntity).collect(toList());
    }

}
