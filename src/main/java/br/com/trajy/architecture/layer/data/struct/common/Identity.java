package br.com.trajy.architecture.layer.data.struct.common;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Identity<T> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private T id;

}
