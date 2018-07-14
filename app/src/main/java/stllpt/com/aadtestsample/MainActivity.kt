package stllpt.com.aadtestsample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

open class MainActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etPassword : EditText
    lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    open fun initUI() {
        etEmail = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    fun appString(context: Context) = context.getString(R.string.app_name)
}
