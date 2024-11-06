package br.com.trajy.architecture.layer.assembly;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

import java.util.List;

public interface AssemblyMapperInterface<RESOURCE, ENTITY> {

    RESOURCE toResource(ENTITY entity);

    ENTITY toEntity(RESOURCE resource);

     default List<RESOURCE> toResource(List<ENTITY> entities) {
         if(isNull(entities)) {
             return null;
         }
         return entities.stream().map(this::toResource).collect(toList());
     }

    default List<ENTITY> toEntity(List<RESOURCE> resources) {
         if(isNull(resources)) {
             return null;
         }
         return resources.stream().map(this::toEntity).collect(toList());
    }

}
