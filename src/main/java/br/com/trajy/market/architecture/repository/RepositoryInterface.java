package br.com.trajy.market.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryInterface<ID_TYPE, ENTITY> extends JpaRepository<ENTITY, ID_TYPE> {

}
