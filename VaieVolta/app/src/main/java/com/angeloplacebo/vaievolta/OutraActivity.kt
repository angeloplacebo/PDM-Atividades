package com.angeloplacebo.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class OutraActivity : AppCompatActivity() {

    private lateinit var etMensagem: EditText
    private lateinit var btOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outra)

        this.btOk = findViewById(R.id.btOutraOk)
        this.etMensagem = findViewById(R.id.etOutraMensagem)

        btOk.setOnClickListener(OnClickBotao())

        this.etMensagem.setText(intent.getStringExtra("MENSAGEM"))

        Log.e("APP_VAI_VOLTA", "OUTRA - onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("APP_VAI_VOLTA", "OUTRA - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("APP_VAI_VOLTA", "OUTRA - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("APP_VAI_VOLTA", "OUTRA - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("APP_VAI_VOLTA", "OUTRA - onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("APP_VAI_VOLTA", "OUTRA - onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("APP_VAI_VOLTA", "OUTRA - onDestroy")
    }

    inner class OnClickBotao : View.OnClickListener {
        override fun onClick(p0: View?) {
            val it = Intent()
            val msg = this@OutraActivity.etMensagem.text.toString()
            it.putExtra("MENSAGEM_VOLTA",msg)
            setResult(Activity.RESULT_OK,it)
            finish()
        }
    }
}
