package stllpt.com.aadtestsample

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("stllpt.com.aadtestsample", appContext.packageName)
    }

    /**
     * @exception ValidationError The @Rule 'mMainActivityTestActivity' must be public.
     * on using simply @Rule
     * ref :
     * https://stackoverflow.com/questions/29945087/kotlin-and-new-activitytestrule-the-rule-must-be-public
     */
    @get:Rule
    public var mMainActivityTestActivity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkSignIn() {
        onView(withId(R.id.etUserName)).perform(replaceText("A@g.c"))
        onView(withId(R.id.etPassword)).perform(replaceText("hello"))
        onView(withId(R.id.btnSubmit)).perform(click())

    }
}
