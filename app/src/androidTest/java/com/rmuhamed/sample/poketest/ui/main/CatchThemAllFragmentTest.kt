package com.rmuhamed.sample.poketest.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.rmuhamed.sample.poketest.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class CatchThemAllFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(PokeTestActivity::class.java, false, false)

    private val mockedViewModel: AbstractCatchThemAllViewModel = MockedCatchThemAllViewModel()

    @Before
    fun setUp() {
        val fragment = Mockito.spy(CatchThemAllFragment())

        Mockito.`when`(fragment.viewModel).thenReturn(mockedViewModel)

        activityRule.activity.supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    @Test
    fun test_init() {
        onView(withId(R.id.skip_it_button)).check(matches(isDisplayed()))
    }
}