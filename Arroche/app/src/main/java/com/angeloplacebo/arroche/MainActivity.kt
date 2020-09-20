package com.angeloplacebo.arroche

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvLi: TextView
    private lateinit var tvLs: TextView
    private lateinit var etChute: EditText
    private lateinit var btChute: Button
    private lateinit var btRestart: Button
    private lateinit var btInfo: Button
    private lateinit var btVerChutes: Button
    private var Li = 1
    private var Ls = 100
    private var n = 0
    private var chutes = arrayListOf<Int>()
    private var chute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvLi = findViewById(R.id.tvLi)
        tvLs = findViewById(R.id.tvLs)
        etChute = findViewById(R.id.etChute)
        btChute = findViewById(R.id.btChute)
        btRestart = findViewById(R.id.btRestart)
        btInfo = findViewById(R.id.btInfo)
        btVerChutes = findViewById(R.id.btVerChutes)
        gerar()
        update()

        btChute.setOnClickListener() {

            if (etChute.text.toString().trim().length > 0) {
                chute = etChute.text.toString().toInt()
                etChute.setText("")

                val resultado = Intent(this@MainActivity, ResultActivity::class.java)

                if ((chute < Li) || (chute > Ls)) {

                    var dialog = AlertDialog.Builder(this)
                    dialog.setMessage("Observe os limites")
                    dialog.create().show()

                } else {
                    if ((chute == Li) || (chute == Ls) || (chute == n)) {
                        resultado.putExtra("RESULTADO", "perdeu")
                        startActivity(resultado)
                    } else if (chute > n) {
                        Ls = chute
                    } else if (chute < n) {
                        Li = chute
                    }

                    if ((Li == n - 1) && (Ls == n + 1)) {
                        resultado.putExtra("RESULTADO", "ganhou")
                        startActivity(resultado)
                    }
                    chutes.add(chute)
                    update()
                }
            }
        }

        btRestart.setOnClickListener() {
            restart()
        }

        btInfo.setOnClickListener() {
            val infoDialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.about_dialog, null)

            infoDialog.setView(dialogView)

            infoDialog.setPositiveButton("OK") { _, _ ->
            }
            infoDialog.create().show()
        }

        btVerChutes.setOnClickListener() {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Você chutou até agora:")
            dialog.setMessage("${chutes.joinToString(separator = ", ")}")
            dialog.create().show()
        }
    }


    fun gerar() {
        n = Random.nextInt(2, 100)
        Log.i("APP_ARROCHA", n.toString())
    }

    fun update() {
        tvLi.text = "${Li}"
        tvLs.text = "${Ls}"
    }

    fun restart() {
        Li = 1
        Ls = 100
        chutes.clear()
        gerar()
        update()
    }
}