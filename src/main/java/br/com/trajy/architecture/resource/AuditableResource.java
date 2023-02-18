package br.com.trajy.architecture.resource;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableResource<T> extends IdResource<T> {

    private String cratedBy;

    private DateTime createdAt;

    private String modifiedBy;

    private DateTime modifiedAt;

}
