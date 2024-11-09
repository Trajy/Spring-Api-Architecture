package br.com.trajy.architecture.layer.service.utils;


import static br.com.trajy.architecture.config.ApplicationContextStatic.obtainContext;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.valueOf;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ClassUtils.getSimpleName;
import static org.apache.commons.lang3.StringUtils.SPACE;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.service.ServiceAbstract;

public final class ServiceUtils {

    private ServiceUtils() { }

    public static <E extends AuditableEntity<Long>, S extends ServiceAbstract<Long, E>> E getIfExists(E entity, Class<E> entityClazz, Class<S> serviceClass) {
        String entityClazzName = getSimpleName(entityClazz);
        checkArgument(nonNull(entity), entityClazzName.concat(SPACE).concat("entity com id deve ser informado"));
        checkArgument(nonNull(entity.getId()), "id da entity".concat(SPACE).concat(entityClazzName).concat(SPACE).concat("deve ser informado"));
        E foundEntity = obtainContext().getBean(serviceClass).findById(entity.getId());
        checkState(
            nonNull(foundEntity),
            entity.getClass().getSimpleName()
                    .concat(SPACE)
                    .concat("não encontrado para o id:")
                    .concat(SPACE)
                    .concat(valueOf(entity.getId()))
        );
        return foundEntity;
    }

}
