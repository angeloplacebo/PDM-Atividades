package com.angeloplacebo.minhastarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginBottom

class FormActivity : AppCompatActivity() {
    private lateinit var btSalvar : Button
    private lateinit var btCancelar : Button
    private lateinit var tvTitle: TextView
    private lateinit var etNome : EditText
    private lateinit var npPrioridade : NumberPicker
    private lateinit var sStatus : Spinner

    private lateinit var dao: TarefaDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        btSalvar = findViewById(R.id.btFormSalvar)
        btCancelar = findViewById(R.id.btFormCancelar)
        tvTitle = findViewById(R.id.tvFormTitle)
        etNome = findViewById(R.id.etFormNome)
        npPrioridade = findViewById(R.id.npFormPrioridade)
        sStatus = findViewById(R.id.sStatus)

        (sStatus.parent as ViewGroup).removeView(sStatus)

        npPrioridade.maxValue=10
        npPrioridade.minValue=1

        dao = TarefaDAO(this)

        btCancelar.setOnClickListener({finish()})
        btSalvar.setOnClickListener({salvar()})
    }

    private fun salvar(){
        val nome = etNome.text.toString()
        val prioridade = npPrioridade.value
        val status = sStatus.selectedItem.toString()

        val tarefa = Tarefa(nome,prioridade,status)

        val intent = Intent()
        intent.putExtra("Tarefa",tarefa)
        setResult(RESULT_OK,intent)
        finish()
//        dao.inserir(tarefa)
//        Log.i("APP_TAREFAS",dao.get().toString())

    }
}