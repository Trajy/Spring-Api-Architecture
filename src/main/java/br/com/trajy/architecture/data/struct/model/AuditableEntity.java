package br.com.trajy.architecture.data.struct.model;

import br.com.trajy.architecture.data.struct.common.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.contrib.hibernate.PersistentDateTime;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<T> extends Id<T> {

    private String cratedBy;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime createdAt;

    private String modifiedBy;

    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime modifiedAt;

}
