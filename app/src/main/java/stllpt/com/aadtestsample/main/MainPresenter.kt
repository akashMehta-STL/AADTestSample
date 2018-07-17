package stllpt.com.aadtestsample.main

/**
 * Created by stllpt031 on 15/7/18.
 */
class MainPresenter(val view: View) {

    fun insertNote() {
        view.addNote()
    }

    fun loadNotes() {
        view.showProgressView(true)
        view.loadNotes(object : LoadRepositoryData {
            override fun onDataLoaded(notes: ArrayList<Note>) {
                view.showProgressView(false)
                view.showNotes(notes)
            }
        })
    }

    interface View {
        fun addNote()
        fun loadNotes(listener: LoadRepositoryData?)
        fun showProgressView(visibility: Boolean)
        fun showNotes(notes: ArrayList<Note>)
    }

    interface LoadRepositoryData {
        fun onDataLoaded(notes: ArrayList<Note>)
    }

    data class Note(val name: String, val author: String)
}