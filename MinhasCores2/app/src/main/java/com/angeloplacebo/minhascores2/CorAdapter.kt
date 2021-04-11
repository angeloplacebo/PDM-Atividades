package com.angeloplacebo.minhascores2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CorAdapter(var context : Context, var lista : ArrayList<Cor>): BaseAdapter() {

    override fun getCount(): Int {
        return this.lista.size
    }

    override fun getItem(position: Int): Any {
        return this.lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view :View

        if (convertView != null){
            view = convertView
        }else{
            val inflate = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflate.inflate(R.layout.cor_layout,null)
        }

        val ivIcon = view.findViewById<ImageView>(R.id.ivCorIcon)
        val tvNome = view.findViewById<TextView>(R.id.tvCorNome)
        val tvCodigo = view.findViewById<TextView>(R.id.tvCorCodigo)

        val cor = this.lista.get(position)

        ivIcon.setColorFilter(Color.parseColor("#${cor.codigo}"))
        tvNome.text = cor.nome
        tvCodigo.text = cor.codigo

        return view
    }

}