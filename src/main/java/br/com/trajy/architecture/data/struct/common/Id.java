package br.com.trajy.architecture.data.struct.common;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Id<T> {

    @javax.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    private T id;

}
