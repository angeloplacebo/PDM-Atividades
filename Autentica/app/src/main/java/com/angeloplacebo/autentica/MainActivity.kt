package com.angeloplacebo.autentica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText
    private lateinit var btOk: Button
    private lateinit var btCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etUsuario = findViewById(R.id.etUsuario)
        this.etSenha = findViewById(R.id.etSenha)
        this.btOk = findViewById(R.id.btOk)
        this.btCancelar = findViewById(R.id.btCancelar)

        this.btCancelar.setOnClickListener{
            Log.i("APP_AUTENTICA", "i - Cancelou")
            Log.e("APP_AUTENTICA", "e - Cancelou")
            Log.w("APP_AUTENTICA", "w - Cancelou")
            Toast.makeText(this,"voce cancelou",Toast.LENGTH_SHORT).show()
            this.etUsuario.text.clear()
            this.etSenha.text.clear()
        }

        this.btOk.setOnClickListener(OnClickBotao())
    }

    inner class OnClickBotao : View.OnClickListener{
        override fun onClick(v: View?) {
            var usuario = this@MainActivity.etUsuario.text.toString()
            var senha = this@MainActivity.etSenha.text.toString()

            Log.i("APP_AUTENTICA","$usuario - $senha")

            if (usuario == "Admin" && senha == "1234"){
                Toast.makeText(this@MainActivity,"Sucesso",Toast.LENGTH_SHORT).show()
                Log.i("APP_AUTENTICA","Deu certo")
//                startActivity(Intent(this@MainActivity, Activity_2::class.java).putExtra("usuario",usuario))
                setContentView(R.layout.activity_2)
                val boasvindas = findViewById<TextView>(R.id.tvB)
                boasvindas.text = "Bem-vindo "+usuario
            }else{
                Toast.makeText(this@MainActivity,"Erro!",Toast.LENGTH_SHORT).show()
                Log.e("APP_AUTENTICA","Deu Erro!")
            }
        }
    }

}
