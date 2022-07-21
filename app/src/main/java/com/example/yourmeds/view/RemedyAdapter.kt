package com.example.yourmeds.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmeds.R
import com.example.yourmeds.model.RemedyModel

class RemedyAdapter : RecyclerView.Adapter<RemedyAdapter.RemedyHolder>() {

    private var medList: ArrayList<RemedyModel> = ArrayList()

    class RemedyHolder(view: View) : RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var  nome = view.findViewById<TextView>(R.id.tvName)
        private var data = view.findViewById<TextView>(R.id.tvData)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)

        fun bindView(med: RemedyModel){
            id.text = med.id.toString()
            nome.text = med.nome.toString()
            data.text = med.date.toString()
        }
    }

    fun addItems(items: ArrayList<RemedyModel>) {
        this.medList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemedyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meds_items, parent, false)
        return RemedyHolder(view)
    }

    override fun onBindViewHolder(holder: RemedyHolder, position: Int) {
       val med = medList[position]
        holder.bindView(med)
    }

    override fun getItemCount(): Int {
       return medList.size
    }
}