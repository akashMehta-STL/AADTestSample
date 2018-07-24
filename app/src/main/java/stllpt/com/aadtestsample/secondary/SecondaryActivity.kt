package stllpt.com.aadtestsample.secondary

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.support.v4.content.ContextCompat
import android.support.v4.util.Preconditions.checkState
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_secondary.*
import stllpt.com.aadtestsample.R
import stllpt.com.aadtestsample.utils.EspressoIdlingResource
import stllpt.com.aadtestsample.utils.visibility

class SecondaryActivity : AppCompatActivity(), SecondaryPresenter.View {
    private lateinit var tvEmail : TextView
    private lateinit var tvPassword : TextView
    private lateinit var ivProfile : ImageView

    private lateinit var username: String
    private lateinit var password: String

    private lateinit var mPresenter: SecondaryPresenter

    companion object {
        const val USER_NAME = "userName"
        const val PASSWORD = "password"
        const val ACTION_CAPTURE_IMAGE = 2001
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            val imageUrl = "file:///android_asset/atsl-logo.png"
            if (resultCode == Activity.RESULT_OK) {
                ivProfile.visibility = View.VISIBLE

                // The image is loaded in a different thread so in order to UI-test this, an idling resource
                // is used to specify when the app is idle.
                EspressoIdlingResource.increment() // App is busy until further notice.

                // This app uses Glide for image loading
                Glide.with(this)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(object : GlideDrawableImageViewTarget(ivProfile) {
                            override fun onResourceReady(resource: GlideDrawable,
                                                animation: GlideAnimation<in GlideDrawable>) {
                                super.onResourceReady(resource, animation)
                                EspressoIdlingResource.decrement() // Set app as idle.
                            }
                        })
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }


            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_secondary_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menuCaptureImage -> {
                startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                        ACTION_CAPTURE_IMAGE)
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        username = ""
        password = ""
        initUI()
    }

    private fun initUI() {
        tvEmail = findViewById(R.id.tvUserName)
        tvPassword = findViewById(R.id.tvPassword)
        ivProfile  = findViewById(R.id.ivProfile)
    }

    override fun showProgress(visibility: Boolean) {
        pbSecondary.visibility = visibility.visibility()
    }

    override fun showData(visibility: Boolean) {
        llData.visibility = visibility.visibility()
    }

    override fun setupData(secondaryModel: SecondaryModel) {
        secondaryModel.apply {
            tvEmail.text = email
            tvPassword.text = password
            ivProfile.setImageResource(R.mipmap.ic_launcher)
        }
    }

    @VisibleForTesting
    fun getCountingIdlingResource(): IdlingResource {
        return EspressoIdlingResource.getIdlingResource()
    }
}
