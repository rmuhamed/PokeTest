package com.rmuhamed.sample.poketest.data;

import androidx.annotation.Nullable;

import java.util.List;

public interface AsyncResult<T, E> {
    default void onSuccess(@Nullable T result) {
    }

    default void onSuccess(@Nullable List<T> result) {
    }

    void onError(E error);

    default void onPresent(Boolean present) {
    }

    ;
}
