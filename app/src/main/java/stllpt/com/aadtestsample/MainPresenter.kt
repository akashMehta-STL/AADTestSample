package stllpt.com.aadtestsample

/**
 * Created by stllpt031 on 15/7/18.
 */
class MainPresenter(private val view: View) {

    fun insertNote() {
        view.addNote()
    }

    interface View {
        fun addNote()
    }
}