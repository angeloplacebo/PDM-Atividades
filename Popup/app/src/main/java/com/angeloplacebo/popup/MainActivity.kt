package com.angeloplacebo.popup

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btMensagem: Button
    private lateinit var btInput: Button
    private lateinit var btData: Button
    private lateinit var btHora: Button
    private lateinit var btFaixa: Button
    private lateinit var btEscolha: Button
    private lateinit var btUnico: Button
    private lateinit var btVarios: Button
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btMensagem = findViewById(R.id.btMensagem)
        this.btInput = findViewById(R.id.btInput)
        this.btData = findViewById(R.id.btData)
        this.btHora = findViewById(R.id.btHora)
        this.btFaixa = findViewById(R.id.btFaixa)
        this.btEscolha = findViewById(R.id.btEscolha)
        this.btUnico = findViewById(R.id.btUnico)
        this.btVarios = findViewById(R.id.btVarios)

        this.btMensagem.setOnClickListener({ mensagem() })
        this.btInput.setOnClickListener({ input() })
        this.btData.setOnClickListener({ data() })
        this.btHora.setOnClickListener({ hora() })
        this.btFaixa.setOnClickListener({ faixa() })
        this.btEscolha.setOnClickListener({ escolha() })
        this.btUnico.setOnClickListener({ Unico() })
        this.btVarios.setOnClickListener({ varios() })

    }

    fun mensagem() {
        val janela = AlertDialog.Builder(this)
        janela.setTitle("Mensagem")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Que bom!")

        janela.setPositiveButton("OK") { dialogInterface, which ->
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.setNeutralButton("Generico") { dialogInterface, which ->
            Toast.makeText(this, "generico", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun input() {

        val janela = AlertDialog.Builder(this)
        this.view = EditText(this)

        janela.setTitle("Input")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Digite uma frase")
        janela.setView(view)

        janela.setPositiveButton("OK") { dialogInterface, which ->
            val msg = (this.view as EditText).text.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun data() {

        val janela = AlertDialog.Builder(this)
        this.view = DatePicker(this)

        janela.setTitle("DatePicker")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha uma data")
        janela.setView(view)

        janela.setPositiveButton("OK") { dialogInterface, which ->
            val dp = this.view as DatePicker
            val msg = "${dp.dayOfMonth}/${dp.month + 1}/${dp.year}"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun hora() {

        val janela = AlertDialog.Builder(this)
        this.view = TimePicker(this)

        janela.setTitle("TimePicker")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha uma hora")
        janela.setView(view)

        janela.setPositiveButton("OK") { dialogInterface, which ->
            val tp = this.view as TimePicker
            var msg = ""

            if (Build.VERSION.SDK_INT < 23) {
                msg = "${tp.currentHour}:${tp.currentMinute}"
            } else {
                msg = "${tp.hour}:${tp.minute}"
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun faixa() {

        val janela = AlertDialog.Builder(this)
        this.view = SeekBar(this)

        janela.setTitle("Faixa de Valores")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Deslize")
        janela.setView(view)

        janela.setPositiveButton("OK") { dialogInterface, which ->
            val msg = (this.view as SeekBar).progress.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun escolha() {

        val janela = AlertDialog.Builder(this)
        this.view = Switch(this)
        this.view.x = -100F

        janela.setTitle("Escolha")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha um estado")
        janela.setView(view)


        janela.setPositiveButton("OK") { dialogInterface, which ->
            val msg = (this.view as Switch).isChecked.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun Unico() {

        val janela = AlertDialog.Builder(this)
        this.view = RadioGroup(this)
        this.view.x = 100F

        for (i in 1 until 5) {
            val rb = RadioButton(this)
            rb.id = i
            rb.text = "opção " + i.toString()
            (this.view as RadioGroup).addView(rb)
        }

        janela.setTitle("Unico")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Marque uma opção")
        janela.setView(view)

        janela.setPositiveButton("OK") { dialogInterface, which ->
            val rgroup = view as RadioGroup
            var msg = rgroup.findViewById<RadioButton>(rgroup.checkedRadioButtonId)?.text.toString()
            if (msg != "null") {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            } else {
                msg = "Nenhuma opção marcada"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun varios() {

        val janela = AlertDialog.Builder(this)
        this.view = LinearLayout(this)
        (this.view as LinearLayout).orientation = LinearLayout.VERTICAL
        this.view.x = 50F
        this.view.y = -22F


        for (i in 1 until 5) {
            val cb = CheckBox(this)
            cb.text = "opção " + i.toString()
            (this.view as LinearLayout).addView(cb)
        }
        janela.setTitle("Varios")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("selecione as opções")
        janela.setView(view)

        janela.setPositiveButton("OK") { dialogInterface, which ->
            val ll = this.view as LinearLayout
            var msg = ""
            for (i in 0 until 4) {
                val cb = ll.getChildAt(i)
                if ((cb as CheckBox).isChecked) {
                    msg += "${cb.text}, "
                }
            }

            if (msg == "") {
                msg = "nenhuma opção selecionada"
            } else {
                msg = msg.substring(0, msg.length - 2)
                msg += " selecionada(s)"
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        }

        janela.setNegativeButton("Cancelar") { dialogInterface, which ->
            Toast.makeText(this, "cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }
}
