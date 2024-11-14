package br.com.trajy.architecture.layer.assembly;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

import org.apache.commons.collections4.CollectionUtils;
import java.util.List;

public abstract class AssemblyMapperAbstract<RESOURCE, ENTITY> implements AssemblyMapperInterface<RESOURCE, ENTITY> {

    @Override
    public List<RESOURCE> toResource(List<ENTITY> entities) {
        if(isEmpty(entities)) {
            return null;
        }
        return entities.stream().map(this::toResource).collect(toList());
    }

    @Override
    public List<ENTITY> toEntity(List<RESOURCE> resources) {
        if(isEmpty(resources)) {
            return null;
        }
        return resources.stream().map(this::toEntity).collect(toList()));
    }

}
