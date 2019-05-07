package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.RestApiConfigurator;
import com.rmuhamed.sample.poketest.config.RestApiDefinition;
import com.rmuhamed.sample.poketest.model.Error;
import com.rmuhamed.sample.poketest.model.Pokemon;

public class RepositoryFactory {

    public enum Type {
        NETWORK,
        DATABASE
    }

    public static IRepository<Pokemon, Error> create(Type type) {
        IRepository<Pokemon, Error> repo = null;
        switch (type) {
            case NETWORK:
                RestApiDefinition api = RestApiConfigurator.createApi("https://pokeapi.co/api/v2/");
                repo = new RestApiRepository(api);
                break;

            case DATABASE:
                repo = new DatabaseRepository();
                break;

            default:
                throw new RuntimeException("Implmentation not available");
        }

        return repo;
    }
}
