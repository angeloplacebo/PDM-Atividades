package com.angeloplacebo.desafio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvTip1: TextView
    private lateinit var tvTip2: TextView
    private lateinit var tvTip3: TextView
    private lateinit var btChutar: Button
    private lateinit var btInfo: Button
    private lateinit var npChute: NumberPicker
    private var n: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTip1 = findViewById(R.id.tvTip01)
        tvTip2 = findViewById(R.id.tvTip02)
        tvTip3 = findViewById(R.id.tvTip03)
        btChutar = findViewById(R.id.btChutar)
        btInfo = findViewById(R.id.btInfo)

        btInfo.setOnClickListener(){
            val infoDialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.about_dialog, null)

            infoDialog.setView(dialogView)

            infoDialog.setPositiveButton("OK") { _, _ ->
            }
            infoDialog.create().show()
        }

        btChutar.setOnClickListener() {
            pickNumber()
        }

        genNumber()
    }

    private fun genNumber() {
        n = Random.nextInt(1, 100)
        Log.i("APP_ACERTE", n.toString())
        genTips()
    }

    private fun genTips() {
        var divs = 0
        val divsInRange10 = arrayListOf<Int>()

        for (i in 1..10) {
            if (n % i == 0) {
                divsInRange10.add(i)
            }
        }

        tvTip1.setText("Divisível por " + divsInRange10.joinToString(separator = ", "))

        if (n % 2 == 0) {
            tvTip2.setText("É par")
        } else {
            tvTip2.setText("É ímpar")
        }

        for (i in 1..n) {
            if (n % i == 0) {
                divs++
            }
        }

        tvTip3.setText("Quantidade de divisores: " + divs.toString())
    }

    private fun pickNumber() {

        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.inflater_dialog, null)

        npChute = dialogView.findViewById(R.id.npChute)
        npChute.maxValue=100
        npChute.minValue=1

        dialog.setView(dialogView)

        dialog.setPositiveButton("OK") { _, _ ->
            npChute.clearFocus()
            val np = npChute.value

            val check = String(Character.toChars(0x2705))
            val wrong = String(Character.toChars(0x274C))

            val msg = if (np == n) check+" Parabéns você acertou" else wrong+" Você errou o número era "+n

            val resultDialog = AlertDialog.Builder(this)
            resultDialog.setMessage(msg)
            resultDialog.create().show()

            genNumber()
        }

        dialog.setNegativeButton("Cancelar") { _, _ ->
            // User cancelled the dialog
        }

        dialog.create().show()

    }

    override fun onRestart() {
        super.onRestart()
        genNumber()
    }
}