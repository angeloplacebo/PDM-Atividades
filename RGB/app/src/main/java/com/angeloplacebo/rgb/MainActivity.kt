package com.angeloplacebo.rgb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var tvRed:TextView
    private lateinit var tvGreen:TextView
    private lateinit var tvBlue:TextView
    private lateinit var receiver : MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRed=findViewById(R.id.tvRed)
        tvGreen=findViewById(R.id.tvGreen)
        tvBlue=findViewById(R.id.tvBlue)

        receiver = MyReceiver()

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)

        registerReceiver(receiver,filter)

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent)
                }
            }
        }

    }

    private fun handleSendText(intent: Intent) {

        val msg = intent.getStringExtra(Intent.EXTRA_TEXT) as String
        tvRed.text = msg
        tvGreen.text = msg
        tvBlue.text = msg

    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    inner class MyReceiver : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_SCREEN_ON){
                Toast.makeText(this@MainActivity,"Tela Desbloqueada",Toast.LENGTH_SHORT).show()
                tvRed.text = "Red"
                tvGreen.text = "Green"
                tvBlue.text = "Blue"
            }
        }
    }
}