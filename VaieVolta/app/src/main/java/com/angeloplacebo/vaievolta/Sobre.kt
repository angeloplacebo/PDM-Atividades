package com.angeloplacebo.vaievolta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Sobre : AppCompatActivity() {

    private lateinit var ivMe : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        ivMe = findViewById(R.id.ivMe)
        ivMe.setOnClickListener{
            finish()
        }
    }
}
