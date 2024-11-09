package br.com.trajy.architecture.layer.assembly.utils;

import static br.com.trajy.architecture.config.ApplicationContextStatic.obtainContext;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import br.com.trajy.architecture.layer.assembly.AssemblyMapperInterface;
import java.util.List;
import java.util.function.Consumer;

public final class AssemblyUtils {

    private AssemblyUtils() { }

    public static <SOURCE, DESTINATION, A extends AssemblyMapperInterface> List<DESTINATION> preventCiclicReference(List<SOURCE> sources, Consumer<SOURCE> actionFunc, Class<A> assemblyClazz) {
        if(isEmpty(sources)) {
            return null;
        }
        A assembly = obtainContext().getBean(assemblyClazz);
        return sources.stream()
                .map(entity -> {
                    actionFunc.accept(entity);
                    return (DESTINATION) assembly.toResource(entity);
                })
                .collect(toList());
    }

    public static <SOURCE, DESTINATION, A extends AssemblyMapperInterface> DESTINATION preventCiclicReference(SOURCE source, Runnable actionFunc, Class<A> assemblyClazz) {
        if(isNull(source)) {
            return null;
        }
        A assembly = obtainContext().getBean(assemblyClazz);
        actionFunc.run();
        return (DESTINATION) assembly.toResource(source);
    }

}
