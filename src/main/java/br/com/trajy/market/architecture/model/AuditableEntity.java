package br.com.trajy.market.architecture.model;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<T> extends PersistableEntity<T> {

    private String cratedBy;

    private DateTime createdAt;

    private String modifiedBy;

    private DateTime modifiedAt;

}
