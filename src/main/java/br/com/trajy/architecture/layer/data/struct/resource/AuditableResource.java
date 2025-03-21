package br.com.trajy.architecture.layer.data.struct.resource;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableResource<T> extends Identity<T> {

    private String createdBy;

    private ZonedDateTime createdAt;

    private String createdIp;

    private String modifiedBy;

    private ZonedDateTime modifiedAt;

    private String modifiedIp;



}
