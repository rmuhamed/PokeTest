package com.rmuhamed.sample.poketest.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rmuhamed.sample.poketest.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PokeTestActivityTest {

    @get:Rule
    val mainActivityScenarioRule: ActivityScenarioRule<PokeTestActivity> =
        ActivityScenarioRule(PokeTestActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun test_first_view_state() {
        onView(withId(R.id.search_and_catch)).check(matches(isDisplayed()))
        onView(withId(R.id.my_backpack)).check(matches(isDisplayed()))
    }
}