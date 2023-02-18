package br.com.trajy.architecture.model;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class PersistableEntity<T> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private T id;

}
