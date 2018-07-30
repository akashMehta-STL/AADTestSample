package stllpt.com.aadtestsample.list

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list.*
import stllpt.com.aadtestsample.R
import stllpt.com.aadtestsample.utils.EspressoIdlingResource
import stllpt.com.aadtestsample.utils.visibility
import stllpt.com.aadtestsample.utils.visible

class ListActivity : AppCompatActivity(), ListPresenter.View {

    lateinit var mPresenter: ListPresenter
    val itemList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        mPresenter = ListPresenter(this)
        lvContent.adapter = ArrayAdapter(this, R.layout.list_item_row, R.id.tvItemName, itemList)
        mPresenter.populateData()
    }

    override fun onDataFetch(list: ArrayList<String>) {
        itemList.addAll(list)
        lvContent.visible()
        showProgress(false)
        (lvContent.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }

    override fun showProgress(visibility: Boolean) {
        pbLoader.visibility = visibility.visibility()
    }

    @VisibleForTesting
    fun getCountingIdlingResource(): IdlingResource {
        return EspressoIdlingResource.getIdlingResource()
    }
}
