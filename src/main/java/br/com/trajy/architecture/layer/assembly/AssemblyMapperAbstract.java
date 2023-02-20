package br.com.trajy.architecture.layer.assembly;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

import java.util.List;

public abstract class AssemblyMapperAbstract<RESOURCE, ENTITY> implements AssemblyMapperInterface<RESOURCE, ENTITY> {

    @Override
    public List<RESOURCE> toResource(List<ENTITY> entities) {
        return emptyIfNull(entities.stream().map(this::toResource).collect(toList()));
    }

    @Override
    public List<ENTITY> toEntity(List<RESOURCE> resources) {
        return emptyIfNull(resources.stream().map(this::toEntity).collect(toList()));
    }

}
