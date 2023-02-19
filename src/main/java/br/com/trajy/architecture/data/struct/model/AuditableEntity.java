package br.com.trajy.architecture.data.struct.model;

import br.com.trajy.architecture.data.struct.common.Id;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<T> extends Id<T> {

    private String cratedBy;

    private DateTime createdAt;

    private String modifiedBy;

    private DateTime modifiedAt;

}
