package com.angeloplacebo.minhascores2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvCores: ListView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: ArrayList<Cor>

    private var CADASTRO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvCores = findViewById(R.id.lvCores)
        fabAdd = findViewById(R.id.fabAdd)

        lista = ArrayList()
        fabAdd.setOnClickListener { add() }

        lvCores.adapter = CorAdapter(this, lista)

        lvCores.onItemLongClickListener = clickLongo()

    }

    private fun add() {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivityForResult(intent, CADASTRO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CADASTRO) {
            if (resultCode == RESULT_OK) {
                val cor = data?.getSerializableExtra("COR") as Cor
                lista.add(cor)
                atualiza()
            }
        }
    }

    private fun atualiza() {
        (lvCores.adapter as BaseAdapter).notifyDataSetChanged()
    }

    inner class clickLongo : AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            this@MainActivity.lista.removeAt(position)
            atualiza()
            return true
        }

    }
}