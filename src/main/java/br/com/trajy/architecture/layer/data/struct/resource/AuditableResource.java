package br.com.trajy.architecture.layer.data.struct.resource;

import br.com.trajy.architecture.layer.data.struct.common.Id;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableResource<T> extends Id<T> {

    private String cratedBy;

    private DateTime createdAt;

    private String modifiedBy;

    private DateTime modifiedAt;

}
