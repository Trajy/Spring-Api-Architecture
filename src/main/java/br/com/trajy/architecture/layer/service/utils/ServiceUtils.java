package br.com.trajy.architecture.layer.service.utils;


import static br.com.trajy.architecture.config.ApplicationContextStaticConfig.obtainContext;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.SPACE;

import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import br.com.trajy.architecture.layer.service.ServiceAbstract;

public final class ServiceUtils {

    private ServiceUtils() { }

    public static <E extends AuditableEntity<Long>, S extends ServiceAbstract<Long, E>> E getIfExists(E entity, Class<S> serviceClass) {
        String clazzName = entity.getClass().getSimpleName();
        checkNotNull(entity, clazzName.concat(SPACE).concat("entity com id deve ser informado"));
        checkNotNull(entity.getId(), "id da entity".concat(SPACE).concat(clazzName).concat(SPACE).concat("deve ser informado"));
        return checkNotNull(
            obtainContext().getBean(serviceClass).findById(entity.getId()),
            entity.getClass().getSimpleName()
                    .concat(SPACE)
                    .concat("não encontrado para o id:")
                    .concat(SPACE)
                    .concat(valueOf(entity.getId()))
        );
    }

}
