package com.angeloplacebo.minhascores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var fbAdd: FloatingActionButton
    private lateinit var lvCores: ListView
    private var cAdapter: ListView_Adapter? = null
    private var itemsList = ArrayList<ListView_Model>()
    private var addRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fbAdd = findViewById(R.id.fbAdd)
        lvCores = findViewById(R.id.lvCores)



        fbAdd.setOnClickListener {
            startActivityForResult(
                Intent(this@MainActivity, AddActivity::class.java),
                addRequestCode
            )
        }

        lvCores.setOnItemClickListener(clickCurto())
        lvCores.setOnItemLongClickListener(clickLongo())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addRequestCode) {
            if (resultCode == RESULT_OK) {
                val colorName = data?.getStringExtra("colorName")
                val colorCode = data?.getStringExtra("colorCode")
                addToList(colorName.toString(), colorCode.toString())
            }
        }
    }

    private fun addToList(colorName: String, colorCode: String) {

        val item = ListView_Model(colorName, colorCode)
        itemsList.add(item)
        lvCores = findViewById(R.id.lvCores) as ListView
        cAdapter = ListView_Adapter(this, itemsList)

        lvCores.adapter = cAdapter
        atualizar()
    }


    inner class clickLongo : AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            this@MainActivity.itemsList.removeAt(position)
            atualizar()
            return true
        }

    }

    inner class clickCurto : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val cor = this@MainActivity.itemsList[position]
            Toast.makeText(this@MainActivity, cor.getColor(), Toast.LENGTH_SHORT).show()
            atualizar()
        }
    }

    private fun atualizar() {
        (this.lvCores.adapter as ListView_Adapter).notifyDataSetChanged()
    }
}