package br.com.trajy.architecture.assembly;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

import java.util.List;

public interface AssemblyMapperInterface<RESOURCE, ENTITY> {

    RESOURCE toResource(ENTITY entity);
    ENTITY toEntity(RESOURCE resource);

     List<RESOURCE> toResource(List<ENTITY> entities);

    List<ENTITY> toEntity(List<RESOURCE> resources);

}
