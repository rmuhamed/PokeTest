package com.rmuhamed.sample.poketest.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rmuhamed.sample.poketest.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyBackpackFragmentTest {

    @get:Rule
    val mainActivityScenarioRule: ActivityScenarioRule<PokeTestActivity> =
        ActivityScenarioRule(PokeTestActivity::class.java)

    @Before
    fun setup() {
        onView(withId(R.id.my_backpack)).perform(click())
    }

    @Test
    fun test_first_view_state() {
        onView(withId(R.id.my_pokemons)).check(matches(isDisplayed()))
    }
}