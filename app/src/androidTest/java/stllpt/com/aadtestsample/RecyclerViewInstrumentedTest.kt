package stllpt.com.aadtestsample

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import stllpt.com.aadtestsample.secondary.SecondaryActivity
import stllpt.com.aadtestsample.secondary.SecondaryAdapter
import stllpt.com.aadtestsample.util.TestUtils

/**
 * Created by stllpt031 on 30/7/18.
 */
@RunWith(AndroidJUnit4::class)
class RecyclerViewInstrumentedTest {
    @get:Rule
    public val mSecondaryTestIntent: IntentsTestRule<SecondaryActivity> = IntentsTestRule<SecondaryActivity>(SecondaryActivity::class.java)

    @Before
    fun registerIdlingResources() {
        IdlingRegistry.getInstance().register(mSecondaryTestIntent.activity.getCountingIdlingResource())
    }
    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(mSecondaryTestIntent.activity.getCountingIdlingResource())
    }
    /**
     * RecyclerView Content test
     */
    @Test
    fun checkAdapterView() {
        Espresso.onView(ViewMatchers.withId(R.id.rvContent)).perform(RecyclerViewActions
                .actionOnItemAtPosition<SecondaryAdapter.ViewHolder>(13, ViewActions.click()))
    }

    @Test
    fun checkRecyclerViewContent() {
        Espresso.onView(TestUtils.withRecyclerView(R.id.rvContent).atPositionOnView(3, R.id.tvName)).check(ViewAssertions.matches(ViewMatchers.withText("email 3")))

    }
}