package stllpt.com.aadtestsample.secondary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.widget.ImageView
import android.widget.TextView
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        username = intent.extras.getString(USER_NAME)
        password = intent.extras.getString(PASSWORD)
        initUI()

        val secondaryModel = SecondaryModel(username, password)
        mPresenter = SecondaryPresenter(this)
        mPresenter.showData(secondaryModel)
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
