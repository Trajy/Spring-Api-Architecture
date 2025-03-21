package br.com.trajy.architecture.layer.data.struct.model;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity<T> extends Identity<T> {

    @Column(updatable = false)
    private String createdBy;

    @Column(updatable = false)
    private ZonedDateTime createdAt;

    private String createdIp;

    private String modifiedBy;

    private ZonedDateTime modifiedAt;

    private String modifiedIp;


}
