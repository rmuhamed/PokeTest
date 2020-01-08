package com.rmuhamed.sample.poketest.ui.detail

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rmuhamed.sample.poketest.R
import com.rmuhamed.sample.poketest.ui.IntentConstants
import com.rmuhamed.sample.poketest.ui.main.mock.MockPokemonFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokeDetailActivityTest {
    private val pokeIntent = Intent(ApplicationProvider.getApplicationContext(), PokeDetailActivity::class.java).apply {
        putExtra(IntentConstants.POKEMON, MockPokemonFactory.create("1", "Fake"))
    }

    @get:Rule
    val scenario = ActivityScenarioRule<PokeDetailActivity>(pokeIntent)

    @Test
    fun test_init_view() {
        onView(withId(R.id.pokemon_picture_image)).check(matches(isDisplayed()))
        onView(withId(R.id.pokemon_type_label)).check(matches(isDisplayed()))
        onView(withId(R.id.pokemon_base_experience_label)).check(matches(isDisplayed()))
        onView(withId(R.id.pokemon_weight_label)).check(matches(isDisplayed()))
        onView(withId(R.id.pokemon_height_label)).check(matches(isDisplayed()))
    }
}