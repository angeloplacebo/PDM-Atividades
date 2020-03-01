package com.angeloplacebo.autentica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class Activity_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val username = intent.getStringExtra("usuario")
        val boasvindas = findViewById<TextView>(R.id.tvB)
        boasvindas.text = "Bem-vindo "+username

    }
}
