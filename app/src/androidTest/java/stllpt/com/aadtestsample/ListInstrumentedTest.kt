package stllpt.com.aadtestsample

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.intent.matcher.BundleMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.core.AllOf
import org.hamcrest.core.Is
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import stllpt.com.aadtestsample.list.ListActivity
import java.util.ArrayList

/**
 * Created by aakash on 29/7/18.
 */
@RunWith(AndroidJUnit4::class)
class ListInstrumentedTest {

    @get:Rule
    public val mListTestIntent :IntentsTestRule<ListActivity> = IntentsTestRule<ListActivity>(ListActivity::class.java)

    @Before
    fun idelingResources() {
        IdlingRegistry.getInstance().register(mListTestIntent.activity.getCountingIdlingResource())
    }

    @Test
    fun testListScroll() {
//        Espresso.onData(withItemContent)
    }

    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(mListTestIntent.activity.getCountingIdlingResource())
    }

}