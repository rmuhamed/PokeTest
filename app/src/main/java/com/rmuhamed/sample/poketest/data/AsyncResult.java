package com.rmuhamed.sample.poketest.data;

import androidx.annotation.Nullable;

public interface AsyncResult<T, E> {
    void onSuccess(@Nullable T result);

    void onError(E error);

    void onPresent(Boolean present);
}
