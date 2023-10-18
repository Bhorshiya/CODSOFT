package com.example.arrrectask

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.design.view.*

class ArrAdapter(val mcontext : Context,val list: List<Model>):RecyclerView.Adapter<ArrAdapter.ViewHolder>() {
    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
    private var itemClickListener: ItemClickListener? = null
    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        fun bind(community: Model) {
            itemView.tv1.text = community.name
            itemView.tv2.text = community.date
            itemView.tv3.text = community.time
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(mcontext).inflate(R.layout.design,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun setItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }
}
