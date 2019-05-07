package com.rmuhamed.sample.poketest.data;

public interface IRepository<T, E> {

    AsyncResult<T, E> findBy(Integer id);

    AsyncResult<T, E> getAll();

    Boolean save(T toBeSaved);

    void addObserver(AsyncResult<T, E> observer);
}
