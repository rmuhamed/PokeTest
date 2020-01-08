package com.rmuhamed.sample.poketest.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rmuhamed.sample.poketest.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatchThemAllFragmentTest {

    @get:Rule
    val mainActivityScenarioRule: ActivityScenarioRule<PokeTestActivity> =
        ActivityScenarioRule(PokeTestActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.search_and_catch)).perform(click())
    }

    @Test
    fun test_first_view_state() {
        onView(withId(R.id.pokemon_card_view)).check(matches(isDisplayed()))
    }
}