package com.rmuhamed.sample.poketest.config;

import android.content.Context;
import org.jetbrains.annotations.NotNull;

public class AppConfigurator {
    private static boolean INITIALIZED;

    private final AppDatabase appDatabase;
    private final RestApiDefinition apiDefinition;

    private AppConfigurator(Context context, String baseUrl) {
        if (INITIALIZED) {
            throw new RuntimeException("Already initialized App Configuration");
        } else {
            INITIALIZED = true;

            this.appDatabase = DatabaseConfigurator.getInstance(context);
            this.apiDefinition = RestApiConfigurator.createApi(baseUrl);
        }
    }

    public static AppConfigurator initialize(@NotNull Context context, @NotNull String baseUrl) {
        return new AppConfigurator(context, baseUrl);
    }


    public RestApiDefinition getApiDefinition() {
        return apiDefinition;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
