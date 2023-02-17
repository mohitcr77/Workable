package com.example.workable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(private val workList: ArrayList<work_data>): RecyclerView.Adapter<adapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)

        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapter.viewHolder, position: Int) {

        val work : work_data = workList[position]
        holder.tv1.text = work.title
        holder.tv2.text = work.location
        holder.tv3.text = work.phno
        holder.tv4.text = work.fee
        holder.tv5.text = work.time
    }

    override fun getItemCount(): Int {
        return workList.size
    }

    public class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val tv1 : TextView = itemView.findViewById(R.id.titleview)
        val tv2 : TextView = itemView.findViewById(R.id.locview2)
        val tv3 : TextView = itemView.findViewById(R.id.feeview)
        val tv4 : TextView = itemView.findViewById(R.id.amtview2)
        val tv5 : TextView = itemView.findViewById(R.id.timeview)
    }
}