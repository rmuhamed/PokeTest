package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.AppConfiguration;
import com.rmuhamed.sample.poketest.model.Pokemon;

import org.jetbrains.annotations.NotNull;

public class RepositoryFactory {
    private AppConfiguration configuration;

    public enum Type {NETWORK, DATABASE}

    private RepositoryFactory(@NotNull Builder builder) {
        this.configuration = builder.configuration;
    }

    public IRepository<Pokemon> create(@NotNull Type type) {
        IRepository<Pokemon> repo;

        switch (type) {
            case NETWORK:
                repo = new RestApiRepository(this.configuration.getApiDefinition(), this.configuration.getThreadExecutor());
                break;

            case DATABASE:
                repo = new DatabaseRepository(this.configuration.getAppDatabase().pokemonDAO(), this.configuration.getThreadExecutor());
                break;

            default:
                throw new RuntimeException("Implementation not available");
        }

        return repo;
    }

    public static class Builder {
        private AppConfiguration configuration;

        public Builder configuration(AppConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        public RepositoryFactory build() {
            return new RepositoryFactory(this);
        }
    }
}
