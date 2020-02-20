package com.angeloplacebo.expobre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var tvNumeros : TextView
    private lateinit var btnSortear : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvNumeros = findViewById(R.id.tvNumeros)
        this.btnSortear = findViewById(R.id.btnSortear)

        this.btnSortear.setOnClickListener(){
            sortear()
        }
    }

    fun sortear(){
        this.tvNumeros.text=megasena().joinToString("  ")
    }

    fun megasena():List<Int>{
        val random = Random()
        var numeros = mutableSetOf<Int>()

        while (numeros.size <6){
            numeros.add(random.nextInt(60)+1)
        }
        return numeros.toList().sorted()
    }
}
