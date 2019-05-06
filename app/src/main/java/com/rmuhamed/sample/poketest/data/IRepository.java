package com.rmuhamed.sample.poketest.data;

import java.util.List;

public interface IRepository<T, E> {

    AsyncResult<T, E> findBy(Integer id);

    AsyncResult<T, E> getAll();

    Boolean save(T toBeSaved);
}
