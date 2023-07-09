package org.example.repositories;

import java.util.List;

public interface CrudRepository<T> {
    void save(T model);

    List<T> findAll();
}
