package stllpt.com.aadtestsample.list

/**
 * Created by aakash on 29/7/18.
 */
public class ListPresenter(val view: View) {
    fun populateData() {
        view.showProgress(true)
        val itemList = ArrayList<String>()
        for (i in 0 until 24) {
            itemList.add("user $i")
        }
        view.onDataFetch(itemList)
    }

    interface View {

        fun onDataFetch(list: ArrayList<String>)
        fun showProgress(visibility: Boolean)
    }
}