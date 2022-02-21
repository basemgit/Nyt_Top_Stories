
package com.basemibrahim.nyt_top_stories

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.basemibrahim.nyt_top_stories.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun happyPath() {
        ActivityScenario.launch(MainActivity::class.java)

        try {
            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.list)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

       onView(withId(R.id.image)).check(matches(isDisplayed()))
    }
}
