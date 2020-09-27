package com.angeloplacebo.minhascores

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView



public class ListView_Adapter(private val context: Context,private val listViewArrayList: ArrayList<ListView_Model>) : BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getCount(): Int {
        return listViewArrayList.size
    }

    override fun getItem(position: Int): Any {
        return listViewArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        val holder : ViewHolder
        if (itemView == null){
            holder = ViewHolder()
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.list_view_item_layout,null,true)

            holder.name = itemView!!.findViewById(R.id.etItemName) as TextView

            itemView.tag = holder
        }else{
            holder = itemView.tag as ViewHolder
        }
        holder.name!!.setText(listViewArrayList[position].getColorName())



        var ccode = listViewArrayList[position].getColorCode()

        holder.name!!.setBackgroundColor(Color.parseColor("#${ccode}"))

        return itemView
    }

    private inner class ViewHolder {
        var name: TextView? = null
    }

}

