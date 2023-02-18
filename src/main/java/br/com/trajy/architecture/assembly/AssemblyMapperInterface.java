package br.com.trajy.architecture.assembly;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface AssemblyMapperInterface<RESOURCE, ENTITY> {

    RESOURCE toResource(ENTITY entity);
    ENTITY toEntity(RESOURCE resource);

     List<RESOURCE> toResource(List<ENTITY> entities);

    List<ENTITY> toEntity(List<RESOURCE> resources);

}
