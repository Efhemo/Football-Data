package com.efhems.football.ui.home


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.efhems.football.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val constraintLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rc_today_fixtures),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        val constraintLayout2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rc_today_fixtures),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        constraintLayout2.perform(click())

        val constraintLayout3 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rc_today_fixtures),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        constraintLayout3.perform(click())

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.competitionsFragment), withContentDescription("Competition"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val constraintLayout4 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rc_competition),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        constraintLayout4.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val actionBarTab = onView(
        allOf(
            withContentDescription("Fixtures"), childAtPosition(
                childAtPosition(
                    withId(R.id.tabs),
                    0
                ),
                1
            ),
            isDisplayed()
        ))

        actionBarTab.check(matches(isDisplayed()))

        val actionBarTab2 = onView(
        allOf(
            withContentDescription("Fixtures"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.tabs),
                    0
                ),
                1
            ),
            isDisplayed()
        ))
        actionBarTab2.check(matches(isDisplayed()))

        val actionBarTab3 = onView(
        allOf(
            withContentDescription("Teams"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.tabs),
                    0
                ),
                2
            ),
            isDisplayed()
        ))
        actionBarTab3.check(matches(isDisplayed()))

        val viewGroup = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rc_competition),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        val actionBarTab4 = onView(
        allOf(
            withContentDescription("Table"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.tabs),
                    0
                ),
                0
            ),
            isDisplayed()
        ))
        actionBarTab4.check(matches(isDisplayed()))

        val actionBarTab5 = onView(
        allOf(
            withContentDescription("Table"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.tabs),
                    0
                ),
                0
            ),
            isDisplayed()
        ))
        actionBarTab5.check(matches(isDisplayed()))

        val tabView = onView(
            allOf(
                withContentDescription("Fixtures"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_retry), withText("Retry"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.swipe_refresh),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val constraintLayout5 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rc_competition),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    11
                ),
                isDisplayed()
            )
        )
        constraintLayout5.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val tabView2 = onView(
            allOf(
                withContentDescription("Teams"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
