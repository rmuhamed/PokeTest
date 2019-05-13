package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.RestApiDefinition;
import com.rmuhamed.sample.poketest.data.dto.PokemonDTO;
import com.rmuhamed.sample.poketest.data.mappers.Mappers;
import com.rmuhamed.sample.poketest.model.Error;
import com.rmuhamed.sample.poketest.model.Pokemon;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApiRepository implements IRepository<Pokemon, Error> {

    private static final String IMPLEMENTATION_ABSENT_ERROR = "Not applicable for this kind of repo";
    private final RestApiDefinition apiDefinition;
    private AsyncResult<Pokemon, Error> observer;

    RestApiRepository(RestApiDefinition apiDefinition) {
        this.apiDefinition = apiDefinition;
    }

    @Override
    public void addObserver(AsyncResult<Pokemon, Error> observer) {
        this.observer = observer;
    }

    @Override
    public void findBy(Integer id) {
        this.apiDefinition.fetchBy(id).enqueue(new Callback<PokemonDTO>() {
            @Override
            public void onResponse(@NotNull Call<PokemonDTO> call, @NotNull Response<PokemonDTO> response) {
                observer.onSuccess(Mappers.toBusinessObject(response.body()));
            }

            @Override
            public void onFailure(@NotNull Call<PokemonDTO> call, @NotNull Throwable t) {
                observer.onError(new Error(t.getMessage()));
            }
        });
    }
}
