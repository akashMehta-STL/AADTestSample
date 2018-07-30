package stllpt.com.aadtestsample.web

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*
import stllpt.com.aadtestsample.R
import stllpt.com.aadtestsample.utils.EspressoIdlingResource

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        wvContent.settings.javaScriptEnabled = true
        wvContent.loadUrl("file:///android_asset/web_form.html")
        wvContent.requestFocus()
        wvContent.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
    }

    @VisibleForTesting
    fun getCountingIdlingResource() = EspressoIdlingResource.getIdlingResource()
}
