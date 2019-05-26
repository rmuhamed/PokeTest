package com.rmuhamed.sample.poketest.data;

import com.rmuhamed.sample.poketest.config.AppConfigurator;
import com.rmuhamed.sample.poketest.model.Pokemon;
import com.rmuhamed.sample.poketest.ui.PokeTestApplication;
import org.jetbrains.annotations.NotNull;

public class RepositoryFactory {
    private PokeTestApplication application;

    public enum Type {NETWORK, DATABASE}

    private RepositoryFactory(@NotNull Builder builder) {
        this.application = builder.application;
    }

    public IRepository<Pokemon> create(@NotNull Type type) {
        AppConfigurator configurator = this.application.getAppConfigurator();
        IRepository<Pokemon> repo;

        switch (type) {
            case NETWORK:
                repo = new RestApiRepository(configurator.getApiDefinition(), configurator.getThreadExecutor());
                break;

            case DATABASE:
                repo = new DatabaseRepository(configurator.getAppDatabase().pokemonDAO(), configurator.getThreadExecutor());
                break;

            default:
                throw new RuntimeException("Implementation not available");
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
