package com.rmuhamed.sample.poketest.data;

public interface AsyncResult<T, E> {
    void onSuccess(T result);

    void onError(E error);
}
