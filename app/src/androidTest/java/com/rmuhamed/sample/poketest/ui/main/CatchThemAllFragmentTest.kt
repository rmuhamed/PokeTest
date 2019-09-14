package com.rmuhamed.sample.poketest.ui.main

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rmuhamed.sample.poketest.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class CatchThemAllFragmentTest {
    private val mockedViewModel: AbstractCatchThemAllViewModel = MockedCatchThemAllViewModel()

    @Before
    fun setUp() {
        val fragment = Mockito.spy(CatchThemAllFragment())

        launchFragmentInContainer { fragment }
    }

    @Test
    fun test_first_view_state() {
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
        onView(withId(R.id.skip_it_button)).check(matches(isDisplayed()))
    }
}