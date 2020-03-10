package com.angeloplacebo.imagens

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.URL


class MainActivity : AppCompatActivity() {
    private var CAMERA = 1
    private lateinit var btCamera: Button
    private lateinit var btArquivo: Button
    private lateinit var btDownload: Button
    private lateinit var ivImagem: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btCamera = findViewById(R.id.btCamera)
        btArquivo = findViewById(R.id.btArquivo)
        btDownload = findViewById(R.id.btDownload)
        ivImagem = findViewById(R.id.ivImagem)

        btCamera.setOnClickListener { camera() }
        btArquivo.setOnClickListener { arquivo() }
        btDownload.setOnClickListener { showOptions() }

    }

    fun showOptions() {
        val builder = AlertDialog.Builder(this)
        var escolha = ""
        builder.setTitle("Escolha uma resolução: ")

        val opcoes = arrayOf("hdpi", "ldpi", "mdpi", "xhdpi", "xxhdpi", "xxxhdpi")
        builder.setSingleChoiceItems(opcoes, -1) { dInterface: DialogInterface, i: Int ->
            escolha = opcoes[i]
        }

        builder.setNegativeButton("Cancelar"){dialog: DialogInterface?, which: Int ->
            dialog!!.cancel()
        }

        builder.setPositiveButton("OK"){dialog: DialogInterface?, which: Int ->
            if(escolha != "") {
                Toast.makeText(this, "Fazendo download de "+escolha, Toast.LENGTH_SHORT).show()
                download(escolha)
            }else{
                Toast.makeText(this, "Nenhuma opção foi selecionada", Toast.LENGTH_SHORT).show()
            }
        }
        builder.create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {
                this.ivImagem.visibility = View.VISIBLE
                val imagem = data?.extras?.get("data") as Bitmap
                this.ivImagem.setImageBitmap(imagem)
            }
        }
    }

    fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    fun arquivo() {
        this.ivImagem.setImageResource(R.drawable.super_mario)
    }

    fun downloadDaImagem(url: String): Bitmap {
        URL(url).openStream().use {
            val imagem = BitmapFactory.decodeStream(it)
            return imagem
        }
    }

    fun download(option: String) {
        val handler = Handler()
        val url = "http://www.valeria.eti.br/sm/sm_" + option + ".png"
        Thread {
            val imagem = this.downloadDaImagem(url)
            handler.post {
                this.ivImagem.setImageBitmap(imagem)
            }
        }.start()
    }
}
