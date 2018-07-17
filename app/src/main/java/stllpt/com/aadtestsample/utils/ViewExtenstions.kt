package stllpt.com.aadtestsample.utils

import android.view.View
import android.widget.TextView

/**
 * Created by stllpt031 on 16/7/18.
 */
fun TextView.value() = this.text.toString()

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun Boolean.visibility() = if (this) View.VISIBLE else View.GONE