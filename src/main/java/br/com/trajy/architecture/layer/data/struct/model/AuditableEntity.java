package br.com.trajy.architecture.layer.data.struct.model;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity<T> extends Identity<T> {

    @Column(updatable = false)
    private String createdBy;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(updatable = false)
    private DateTime createdAt;

    private String modifiedBy;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modifiedAt;

    private String ip;

}
