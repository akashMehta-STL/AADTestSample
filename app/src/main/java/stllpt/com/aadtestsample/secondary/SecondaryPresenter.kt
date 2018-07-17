package stllpt.com.aadtestsample.secondary

import android.os.Handler

/**
 * Created by stllpt031 on 17/7/18.
 */
class SecondaryPresenter (val view: View){

    fun showData(secondaryModel: SecondaryModel) {
        view.showProgress(true)
        view.showData(false)
        Handler().postDelayed({
            view.showProgress(false)
            view.showData(true)
            view.setupData(secondaryModel)
        }, 500)
    }

    interface View {
        fun showProgress(visibility: Boolean)
        fun showData(visibility: Boolean)
        fun setupData(secondaryModel: SecondaryModel)
    }
}