package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.RestApiDefinition;
import com.rmuhamed.sample.poketest.data.dto.PokemonResponseDTO;
import com.rmuhamed.sample.poketest.model.Error;
import com.rmuhamed.sample.poketest.model.Pokemon;

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

                observer.onSuccess(pokemon);
            }

            @Override
            public void onFailure(Call<PokemonResponseDTO> call, Throwable t) {
                observer.onError(new Error(t.getMessage()));
            }
        });

        return observer;
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
