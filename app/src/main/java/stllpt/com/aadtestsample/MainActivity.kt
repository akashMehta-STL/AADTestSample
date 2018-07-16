package stllpt.com.aadtestsample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

public open class MainActivity : AppCompatActivity(), MainPresenter.View {
    private lateinit var etEmail: EditText
    private lateinit var etPassword : EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    open fun initUI() {
        etEmail = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvOutput = findViewById(R.id.tvOutput)
        btnSubmit.setOnClickListener {
            if (etEmail.value().isNonEmpty()) {
                tvOutput.visible()
                tvOutput.text = etEmail.value()
            }
        }
    }

    override fun addNote() {
        noteAdded()
    }

    private fun noteAdded() {

    }

    override fun loadNotes(listener: MainPresenter.LoadRepositoryData?) {

    }

    override fun showProgressView(visibility: Boolean) {

    }

    override fun showNotes(notes: ArrayList<MainPresenter.Note>) {

    }


    fun appString(context: Context) = context.getString(R.string.app_name)
}
