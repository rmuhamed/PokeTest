package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.AppConfigurator;
import com.rmuhamed.sample.poketest.model.Error;
import com.rmuhamed.sample.poketest.model.Pokemon;
import com.rmuhamed.sample.poketest.ui.PokeTestApplication;

public class RepositoryFactory {
    private PokeTestApplication application;

    public enum Type {NETWORK, DATABASE}

    public RepositoryFactory(Builder builder) {
        this.application = builder.application;
    }

    public IRepository<Pokemon, Error> create(Type type) {
        AppConfigurator configurator = this.application.getAppConfigurator();
        IRepository<Pokemon, Error> repo = null;

        switch (type) {
            case NETWORK:
                repo = new RestApiRepository(configurator.getApiDefinition());
                break;

            case DATABASE:
                repo = new DatabaseRepository(configurator.getAppDatabase().pokemonDAO());
                break;

            default:
                throw new RuntimeException("Implmentation not available");
        }

        return repo;
    }

    public static class Builder {
        private PokeTestApplication application;

        public Builder application(PokeTestApplication application) {
            this.application = application;
            return this;
        }

        public RepositoryFactory build() {
            return new RepositoryFactory(this);
        }
    }
}
