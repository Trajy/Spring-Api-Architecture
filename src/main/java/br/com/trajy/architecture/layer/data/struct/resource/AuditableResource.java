package br.com.trajy.architecture.layer.data.struct.resource;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableResource<T> extends Identity<T> {

    private String createdBy;

    private DateTime createdAt;

    private String createdIp;

    private String modifiedBy;

    private DateTime modifiedAt;

    private String modifiedIp;



}
