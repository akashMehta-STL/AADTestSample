package stllpt.com.aadtestsample.web

import android.content.Intent
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.web.assertion.WebViewAssertions
import android.support.test.espresso.web.sugar.Web
import android.support.test.espresso.web.sugar.Web.onWebView
import android.support.test.espresso.web.webdriver.DriverAtoms
import android.support.test.espresso.web.webdriver.Locator
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by stllpt031 on 30/7/18.
 */
@RunWith(AndroidJUnit4::class)
class WebActivityTest {

    @get:Rule
    public val webActivityTest : ActivityTestRule<WebActivity>
            = object : ActivityTestRule<WebActivity>(WebActivity::class.java, false, false) {
        override fun afterActivityLaunched() {
            onWebView().forceJavascriptEnabled()
        }
    }

    @Test
    fun checkClickEvent() {
        webActivityTest.launchActivity(Intent())
        Web.onWebView()
                .withElement(DriverAtoms.findElement(Locator.ID, "text_input"))
                .perform(DriverAtoms.clearElement())
                .perform(DriverAtoms.webKeys("Hello"))
                .withElement(DriverAtoms.findElement(Locator.ID, "changeTextBtn"))
                .perform(DriverAtoms.webClick())
                .withElement(DriverAtoms.findElement(Locator.ID, "message"))
                .check(WebViewAssertions.webMatches(DriverAtoms.getText(), CoreMatchers.containsString("Hello")))
    }
}