package com.angeloplacebo.diversos

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private val requestCall = 1
    private val requestCam = 2

    private lateinit var btHtml : Button
    private lateinit var btDiscar : Button
    private lateinit var btLigar : Button
    private lateinit var btCompartilhar : Button
    private lateinit var btEmail : Button
    private lateinit var btPonto : Button
    private lateinit var btRota : Button
    private lateinit var btSms : Button
    private lateinit var btYoutube : Button
    private lateinit var btFoto : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btHtml = findViewById(R.id.btHtml)
        btDiscar = findViewById(R.id.btDiscar)
        btLigar = findViewById(R.id.btLigar)
        btCompartilhar = findViewById(R.id.btCompartilhar)
        btEmail = findViewById(R.id.btEmail)
        btPonto = findViewById(R.id.btPonto)
        btRota = findViewById(R.id.btRota)
        btSms = findViewById(R.id.btSms)
        btYoutube = findViewById(R.id.btYoutube)
        btFoto = findViewById(R.id.btFoto)

        btHtml.setOnClickListener{html()}
        btDiscar.setOnClickListener{discar()}
        btLigar.setOnClickListener{ligar()}
        btCompartilhar.setOnClickListener{compartilhar()}
        btEmail.setOnClickListener{email()}
        btPonto.setOnClickListener{ponto()}
        btRota.setOnClickListener{rota()}
        btSms.setOnClickListener{sms()}
        btYoutube.setOnClickListener{youtube()}
        btFoto.setOnClickListener{foto()}

    }
    fun html(){
        val uri = Uri.parse("http://www.ifpb.edu.br")
        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }
    }

    fun discar(){
        val uri = Uri.parse("tel:36121392")
        val intent = Intent(Intent.ACTION_DIAL,uri)
        startActivity(intent)
    }

    fun ligar(){

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),requestCall)

        val uri = Uri.parse("tel:36121392")
        val intent = Intent(Intent.ACTION_CALL, uri)
        val call = Manifest.permission.CALL_PHONE
        val granted = PackageManager.PERMISSION_GRANTED

        if (ContextCompat.checkSelfPermission(this,call)==granted){
            startActivity(intent)
        }else{
            Toast.makeText(this,"Permissões necessárias não concedidas",Toast.LENGTH_LONG).show()
        }
    }

    fun compartilhar(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,"Texto para comparilhar")
        //intent.setPackage("com.whatsapp")

        if (intent.resolveActivity(packageManager)!=null){
            startActivity(Intent.createChooser(intent,"compartilhar"))
        }
    }

    fun email(){
        val uri = Uri.parse("mailto:<valeria.cavalcanti@ifpb.edu.br")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        intent.putExtra(Intent.EXTRA_SUBJECT,"Assunto")
        intent.putExtra(Intent.EXTRA_TEXT,"Texto")

        if(intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }
    }

    fun ponto(){
        val uri = Uri.parse("geo:-7.1356496,-34.8760932")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun rota(){
        val origem = "-7.1356496,-34.8760932"
        val destino = "-7.1181836,-34.8730402"
        val url = "http://maps.google.com/maps"
        val uri = Uri.parse("${url}?f=&saddr=${origem}+&daddr=${destino}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun sms(){
        val uri = Uri.parse("sms:36121392")
        val intent = Intent(Intent.ACTION_SENDTO,uri)
        intent.putExtra("sms_body", "Mensagem")
        startActivity(intent)
    }

    fun youtube(){
        val uri = Uri.parse("vnd.youtube://dglqGGyWbVo")
        val intent = Intent(Intent.ACTION_VIEW,uri)
        if (intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }
    }

    fun foto(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,requestCam)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == requestCam){

                val ivImage = ImageView(this)
                ivImage.setImageBitmap(data?.extras?.get("data") as Bitmap)

                val builder = AlertDialog.Builder(this)
                builder.setMessage("Imagem capturada ")
                builder.setView(ivImage)
                builder.setPositiveButton("OK", null)
                builder.create().show()

            }
        }
    }
}
