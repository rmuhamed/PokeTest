package com.rmuhamed.sample.poketest.data;

public interface IRepository<T, E> {

    default void findBy(Integer id) {
    }

    default void getAll() {
    }

    default void save(T toBeSaved) {
    }

    default void addObserver(AsyncResult<T, E> observer) {
    }

    default void exists(String id) {
    }
}
