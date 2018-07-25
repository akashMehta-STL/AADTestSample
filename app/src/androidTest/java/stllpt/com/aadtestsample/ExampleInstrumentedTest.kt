package stllpt.com.aadtestsample

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.provider.MediaStore
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers
import org.hamcrest.core.IsNot
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import stllpt.com.aadtestsample.main.MainActivity
import stllpt.com.aadtestsample.secondary.SecondaryActivity
import stllpt.com.aadtestsample.util.ImageViewHasDrawableMatcher

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val userName = "A@g.c"
    private val password = "password"
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("stllpt.com.aadtestsample", appContext.packageName)
    }

    @get:Rule
    public val mSecondaryTestIntent: IntentsTestRule<SecondaryActivity> = IntentsTestRule<SecondaryActivity>(SecondaryActivity::class.java)

    @Test
    fun checkSignIn() {
        onView(withId(R.id.etUserName)).perform(typeText(userName))
        onView(withId(R.id.etPassword)).perform(typeText(password))
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.tvOutput)).check(matches(isDisplayed()))
    }

    @Before
    fun registerIdlingResources() {
        IdlingRegistry.getInstance().register(mSecondaryTestIntent.activity.getCountingIdlingResource())
    }

    @Test
    fun addImageNote_showImage() {
        defineCaptureImageAction()

        onView(withId(R.id.ivProfile)).check(matches(IsNot.not(isDisplayed())))

        performOptionMenuClick()

        onView(withId(R.id.ivProfile)).check(matches(isDisplayed()))
    }

    private fun performOptionMenuClick() {
        openActionBarOverflowOrOptionsMenu(mSecondaryTestIntent.activity)

        onView(withText(R.string.menu_title_capture_image)).perform(click())
    }

    private fun defineCaptureImageAction() {
        val result = getActivityResult()
        Intents.intending(IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE))
                .respondWithFunction {
                    println("SecondaryActivity:  Image capture action is called.")
                    result
                }
    }

    private fun getActivityResult(): Instrumentation.ActivityResult {
        return Instrumentation.ActivityResult(Activity.RESULT_OK, null)
    }
//    fun initWithStubbedData() {
//        val intent = Intent()
//        intent.putExtra(SecondaryActivity.USER_NAME, userName)
//        intent.putExtra(SecondaryActivity.PASSWORD, password)
//        mSecondaryActivityTestActivity.launchActivity(intent)
//        registerIdlingResources()
//    }

    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(mSecondaryTestIntent.activity.getCountingIdlingResource())
    }

    @Test
    fun checkDisplayData() {
        onView(withId(R.id.tvUserName)).check(matches(withText(userName)))
        onView(withId(R.id.tvPassword)).check(matches(withText(password)))
        onView(withId(R.id.ivProfile)).check(matches(Matchers.allOf(ImageViewHasDrawableMatcher.hasDrawable(), isDisplayed())))
    }
}
