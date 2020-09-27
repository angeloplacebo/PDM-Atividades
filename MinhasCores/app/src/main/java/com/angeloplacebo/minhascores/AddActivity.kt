package com.angeloplacebo.minhascores

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddActivity : AppCompatActivity() {
    private lateinit var fbClose: FloatingActionButton
    private lateinit var btAdd: Button
    private lateinit var etName: EditText
    private lateinit var etCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        fbClose = findViewById(R.id.fbClose)
        btAdd = findViewById(R.id.btAdd)
        etName = findViewById(R.id.etName)
        etCode = findViewById(R.id.etCode)

        fbClose.setOnClickListener() {
            finish()
        }
        btAdd.setOnClickListener({ add() })
    }

    fun add() {
        val colorName: String = etName.text.toString()
        val colorCode: String = etCode.text.toString()


        try {
            Color.parseColor("#${colorCode}")

            if (colorName != "") {
                val it = Intent()
                it.putExtra("colorName", colorName)
                it.putExtra("colorCode", colorCode)
                setResult(Activity.RESULT_OK, it)
                finish()
            } else {
                Toast.makeText(this, "Insira um nome p/ a cor", Toast.LENGTH_SHORT).show()
            }
        } catch (iae: IllegalArgumentException) {
            Toast.makeText(this, "código invalido", Toast.LENGTH_SHORT).show()
        }


    }
}