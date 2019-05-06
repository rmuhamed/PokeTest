package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.RestApiDefinition;
import com.rmuhamed.sample.poketest.data.dto.PokemonResponseDTO;
import com.rmuhamed.sample.poketest.model.Pokemon;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApiRepository implements IRepository<Pokemon, Error> {

    private static final String IMPLEMENTATION_ABSENT_ERROR = "Not applicable for this kind of repo";
    private final RestApiDefinition apiDefinition;
    private final AsyncResult<Pokemon, Error> asyncResult;

    public RestApiRepository(RestApiDefinition apiDefinition,
                             AsyncResult<Pokemon, Error> asyncResult) {
        this.apiDefinition = apiDefinition;
        this.asyncResult = asyncResult;
    }

    @Override
    public AsyncResult<Pokemon, Error> findBy(Integer id) {
        apiDefinition.fetchBy(id).enqueue(new Callback<PokemonResponseDTO>() {
            @Override
            public void onResponse(Call<PokemonResponseDTO> call, Response<PokemonResponseDTO> response) {
                PokemonResponseDTO dto = response.body();

                Pokemon pokemon = new Pokemon.Builder()
                        .setId(dto.getId())
                        .setHeight(dto.getHeight())
                        .setName(dto.getName())
                        .setWeight(dto.getWeight())
                        .setBaseExperience(dto.getBaseExperience())
                        .setType(dto.getTypes().get(0).getType().getName())
                        .setPicture(dto.getSprites().getFront())
                        .build();

                asyncResult.onSuccess(pokemon);
            }

            @Override
            public void onFailure(Call<PokemonResponseDTO> call, Throwable t) {
                asyncResult.onError(new Error(t.getMessage()));
            }
        });

        return asyncResult;
    }

    @Override
    public AsyncResult<Pokemon, Error> getAll() {
        throw new RuntimeException(IMPLEMENTATION_ABSENT_ERROR);
    }

    @Override
    public Boolean save(Pokemon toBeSaved) {
        throw new RuntimeException(IMPLEMENTATION_ABSENT_ERROR);
    }
}
