package br.com.trajy.architecture.layer.data.struct.model;

import br.com.trajy.architecture.layer.data.struct.common.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity<T> extends Id<T> {

    private String createdBy;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdAt;

    private String modifiedBy;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modifiedAt;

    private String ip;

}
