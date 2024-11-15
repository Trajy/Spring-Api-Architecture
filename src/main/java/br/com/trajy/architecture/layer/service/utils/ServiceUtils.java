package br.com.trajy.architecture.layer.service.utils;


import static br.com.trajy.architecture.config.ApplicationContextStatic.getBean;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ClassUtils.getSimpleName;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.service.ServiceAbstract;

public final class ServiceUtils {

    private ServiceUtils() { }

    public static <E extends AuditableEntity<Long>, S extends ServiceAbstract<Long, E>> E getIfExists(E entity, Class<E> entityClazz, Class<S> serviceClass) {
        String entityClazzName = getSimpleName(entityClazz);
        checkArgument(nonNull(entity) && nonNull(entity.getId()), format("%s with id property must be informed.", entityClazzName));
        E foundEntity = getBean(serviceClass).findById(entity.getId());
        checkState(nonNull(foundEntity), format("%s not found for id: %d", entityClazzName, entity.getId()));
        return foundEntity;
    }

    public static <ID_TYPE, E extends Identity<ID_TYPE>> String formatSaveErrorMessage(E entity) {
        return format("id from %s must be null to save", entity.getClass().getSimpleName());
    }

    public static <ID_TYPE, E extends Identity<ID_TYPE>> String formatUpdateErrorMessage(E entity) {
        return format("id from %s can't be null to update", entity.getClass().getSimpleName());
    }

}
