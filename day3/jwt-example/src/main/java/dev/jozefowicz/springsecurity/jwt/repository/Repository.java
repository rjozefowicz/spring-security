package dev.jozefowicz.springsecurity.jwt.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    void persist(T entity);
}
