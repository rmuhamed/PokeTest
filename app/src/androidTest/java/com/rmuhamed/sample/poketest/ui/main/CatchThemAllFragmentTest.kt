package com.rmuhamed.sample.poketest.ui.main

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.config.AppConfiguration
import com.rmuhamed.sample.poketest.data.RestApiRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class CatchThemAllFragmentTest {

    @Rule
    val mainActivityScenarioRule: ActivityScenarioRule<PokeTestActivity> =
        ActivityScenarioRule(PokeTestActivity::class.java)

    @Before
    fun setUp() {
        val mockedAppConfiguration = Mockito.mock(AppConfiguration::class.java)
        Mockito.`when`(mockedAppConfiguration.networkRepository).thenReturn(
            Mockito.mock(
                RestApiRepository::class.java
            )
        )

        val fragment = Mockito.spy(CatchThemAllFragment())
        Mockito.`when`(fragment.getConfiguration()).thenReturn(mockedAppConfiguration)

        launchFragmentInContainer { fragment }
    }

    @Test
    fun test_first_view_state() {
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
        onView(withId(R.id.skip_it_button)).check(matches(isDisplayed()))
    }
}