package com.rmuhamed.sample.poketest.data;

public interface IRepository<T, E> {

    default AsyncResult<T, E> findBy(Integer id) {
        return null;
    }

    default AsyncResult<T, E> getAll() {
        return null;
    }

    default Boolean save(T toBeSaved) {
        return false;
    }

    void addObserver(AsyncResult<T, E> observer);

    default Boolean exists(String id) {
        return false;
    }
}
