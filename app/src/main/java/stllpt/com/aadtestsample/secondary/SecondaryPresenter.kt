package stllpt.com.aadtestsample.secondary

import android.os.Handler

/**
 * Created by stllpt031 on 17/7/18.
 */
class SecondaryPresenter (val view: View){

    fun showDummyData() {
        val itemList = ArrayList<SecondaryModel>()
        for (i in 0 until 25) {
            itemList.add(SecondaryModel("email $i", "password $i"))
        }
        view.populateData(itemList)
    }

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
        fun populateData(itemList: ArrayList<SecondaryModel>)
        fun setupData(secondaryModel: SecondaryModel)
    }
}