package dev.jozefowicz.springsecurity.application.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E> {

    void persist(E entity);

    List<E> findAll();

    Optional<E> findById(long id);

}
