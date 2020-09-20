package com.angeloplacebo.arroche

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ResultActivity : AppCompatActivity() {
    private lateinit var tvResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        tvResultado = findViewById(R.id.tvResultado)
        val ll = findViewById(R.id.llResult) as LinearLayout

        if(intent.getStringExtra("RESULTADO")=="perdeu"){
            tvResultado.setText("Infelizmente você perdeu :(")
            ll.setBackgroundColor(ContextCompat.getColor(this,R.color.red))
        }else{
            tvResultado.setText("Parabéns você ganhou :)")
            ll.setBackgroundColor(ContextCompat.getColor(this,R.color.green))
        }




    }
}