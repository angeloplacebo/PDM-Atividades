package com.angeloplacebo.minhascores2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    private lateinit var btSalvar : Button
    private lateinit var btCancelar : Button
    private lateinit var etNome : EditText
    private lateinit var etCodigo : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btSalvar = findViewById(R.id.btCadastroSalvar)
        btCancelar = findViewById(R.id.btCadastroCancelar)
        etNome = findViewById(R.id.etCadastroNome)
        etCodigo = findViewById(R.id.etCadastroCodigo)

        btSalvar.setOnClickListener{salvar()}
        btCancelar.setOnClickListener{cancelar()}

    }

    private fun salvar(){
        val nome = etCadastroNome.text.toString()
        val codigo = etCadastroCodigo.text.toString()
        val cor = Cor(nome,codigo)

        val intent = Intent()
        intent.putExtra("COR",cor)
        setResult(RESULT_OK,intent)
        finish()
    }

    private fun cancelar(){
        finish()
    }
}