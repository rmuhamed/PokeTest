package com.rmuhamed.sample.poketest.ui.main.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.rmuhamed.sample.poketest.ui.main.mock.MockPokeTestApplication

class MockAndroidJUnitRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        val mockClassName = MockPokeTestApplication::class.java.name
        return super.newApplication(cl, mockClassName, context)
    }
}