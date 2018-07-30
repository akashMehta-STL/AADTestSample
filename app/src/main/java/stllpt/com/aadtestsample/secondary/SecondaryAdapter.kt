package stllpt.com.aadtestsample.secondary

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_row.view.*
import stllpt.com.aadtestsample.R

/**
 * Created by stllpt031 on 26/7/18.
 */
class SecondaryAdapter(val itemList: ArrayList<SecondaryModel>) : RecyclerView.Adapter<SecondaryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = itemList[position]
        holder.itemView.apply {
            tvName.text = model.email
            tvPass.text = model.password
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}