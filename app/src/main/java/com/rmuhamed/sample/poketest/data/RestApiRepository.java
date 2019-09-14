package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.RestApiDefinition;
import com.rmuhamed.sample.poketest.data.dto.PokemonDTO;
import com.rmuhamed.sample.poketest.data.mappers.Mappers;
import com.rmuhamed.sample.poketest.model.Pokemon;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class RestApiRepository implements IRepository<Pokemon> {
    private final RestApiDefinition apiDefinition;
    private final ExecutorService executorService;

    RestApiRepository(RestApiDefinition apiDefinition, ExecutorService executorService) {
        this.apiDefinition = apiDefinition;
        this.executorService = executorService;
    }

    @Override
    public Future<Pokemon> findBy(String id) {
        Callable<Pokemon> callable = () -> {
            PokemonDTO dto = this.apiDefinition.fetchBy(id).execute().body();
            return Mappers.toBusinessObject(dto);
        };

        return this.executorService.submit(callable);
    }
}
