package com.angeloplacebo.minhastarefas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class TarefaAdapter(private var context: Context, var lista: ArrayList<Tarefa>):BaseAdapter() {
    override fun getCount(): Int {
        return this.lista.size
    }

    override fun getItem(position: Int): Any {
        return this.lista[position]
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
            view = inflate.inflate(R.layout.lv_custom_layout,null)
        }

        val ivIcon = view.findViewById<ImageView>(R.id.ivIcon)
        val tvNome = view.findViewById<TextView>(R.id.tvNome)
        val tvPrioridade = view.findViewById<TextView>(R.id.tvPrioridade)

        val tarefa = this.lista.get(position)

        tvNome.text = tarefa.nome
        tvPrioridade.text = "Nível ${tarefa.prioridade.toInt()} de Prioridade"

        when(tarefa.status){
            "Aberto" -> ivIcon.setImageResource(R.drawable.ic_adjust)
            "Executando" -> ivIcon.setImageResource(R.drawable.ic_play_circle)
            "Concluído" -> ivIcon.setImageResource(R.drawable.ic_check_circle)
        }

        return view
    }
}