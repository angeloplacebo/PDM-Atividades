package com.angeloplacebo.deuruim

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {

    private lateinit var etDescricao : EditText
    private lateinit var sbNota :  SeekBar
    private lateinit var btSalvar : Button
    private lateinit var btCancelar : Button
    private lateinit var btFoto: Button
    private lateinit var ivFoto: ImageView

    var CAMERA = 2
    var hasImage = false
    private lateinit var image : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.ivFoto = findViewById(R.id.ivFormImage)
        this.etDescricao = findViewById(R.id.etFormDescricao)
        this.sbNota = findViewById(R.id.sbFormNota)

        this.btSalvar = findViewById(R.id.btFormSalvar)
        this.btCancelar = findViewById(R.id.btFormCancelar)
        this.btFoto = findViewById(R.id.btFormFoto)


        this.btSalvar.setOnClickListener{salvar()}
        this.btCancelar.setOnClickListener{
            finish()
        }
        this.btFoto.setOnClickListener{foto()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CAMERA){
                hasImage = true
                image = data?.extras?.get("data") as Bitmap
                this.ivFoto.setImageBitmap(image)
            }
        }
    }
    fun salvar(){
        val descricao = this.etDescricao.text.toString()
        val nota = this.sbNota.progress
        var foto = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)

        if (hasImage == true) {
            foto = image
        }

        val evento = Evento(descricao, nota, foto)
        val intent = Intent()
        intent.putExtra("EVENTO",evento)

        setResult(Activity.RESULT_OK,intent)

        finish()
    }
    fun foto(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CAMERA)
    }
}
