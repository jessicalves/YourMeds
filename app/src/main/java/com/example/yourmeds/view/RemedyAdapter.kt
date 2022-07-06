package com.example.yourmeds.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmeds.R
import com.example.yourmeds.model.Remedy
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RemedyAdapter(var remedyes: MutableList<Remedy>) :
    RecyclerView.Adapter<RemedyAdapter.RemedyHolder>() {

    inner class RemedyHolder(view: View) : RecyclerView.ViewHolder(view){
        var editTextNome : TextInputEditText
        var editTextDose : TextInputEditText
        var editTextCor : TextInputLayout
        var editTextData : TextInputEditText
        var editTextDias : TextInputEditText
        var editTextRepetir : TextInputEditText

        init {
            editTextNome = view.findViewById(R.id.editTextNome)
            editTextDose = view.findViewById(R.id.editTextDose)
            editTextCor = view.findViewById(R.id.editTextCor)
            editTextData = view.findViewById(R.id.editTextData)
            editTextDias = view.findViewById(R.id.editTextDias)
            editTextRepetir = view.findViewById(R.id.editTextRepetir)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemedyHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RemedyHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}