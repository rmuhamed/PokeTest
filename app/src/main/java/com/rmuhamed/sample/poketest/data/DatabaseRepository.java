package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.data.dao.PokemonDAO;
import com.rmuhamed.sample.poketest.data.dao.PokemonEntity;
import com.rmuhamed.sample.poketest.data.mappers.Mappers;
import com.rmuhamed.sample.poketest.model.Pokemon;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DatabaseRepository implements IRepository<Pokemon> {

    private final PokemonDAO dao;
    private final ExecutorService executor;

    DatabaseRepository(PokemonDAO dao, ExecutorService executorService) {
        this.dao = dao;
        this.executor = executorService;
    }

    @Override
    public Future<List<Pokemon>> getAll() {
        Callable<List<Pokemon>> callable = () -> Mappers.toBusinessObject(dao.findAll());

        return this.executor.submit(callable);
    }

    @Override
    public Future<Boolean> save(Pokemon toBeSaved) {
        Callable<Boolean> saveCallable = () -> {
            PokemonEntity entity = Mappers.toDBEntity(toBeSaved);
            dao.save(entity);
            return Boolean.TRUE;
        };

        return this.executor.submit(saveCallable);
    }

    @Override
    public Future<Boolean> exists(String id) {
        Callable<Boolean> existsCallable = () -> dao.countBy(id) > 0;

        return this.executor.submit(existsCallable);
    }

    @Override
    public Future<Pokemon> findBy(String id) {
        Callable<Pokemon> findByCallable = () -> Mappers.toBusinessObject(dao.findBy(id));

        return this.executor.submit(findByCallable);
    }
}
