package com.example.yourmeds.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.yourmeds.R
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout

class ChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setTitle("Adicionar")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)

        val autoCor= findViewById<AutoCompleteTextView>(R.id.autoCor)
        val itemsCor = listOf("Nenhuma", "Vermelho", "Azul", "Laranja", "Verde", "Amarelo", "Preto", "Rosa", "Roxo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsCor)
        autoCor.setAdapter(adapter)

        val editTextRepetir = findViewById<TextInputLayout>(R.id.editTextRepetir)
        val swLembrete = findViewById<SwitchMaterial>(R.id.swLembrete)

        swLembrete.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editTextRepetir.isEnabled = true
                editTextRepetir.visibility = View.VISIBLE
                val autoRepetir = findViewById<AutoCompleteTextView>(R.id.autoRepetir)
                val itemsRepetir = listOf("A cada 15 minutos", "A cada 30 minutos", "A cada 1 hora", "A cada 2 horas",
                    "A cada 3 horas", "A cara 4 horas", "A cada 5 horas", "A cada 6  horas",
                    "A cada 8 horas", "A cada 12 horas", "A cada 24 horas", "A cada 2 dias", "A cada 1 semana",
                    "A cada 15 dias", "A cada 1 mês")
                val adapterRepete = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsRepetir)
                autoRepetir.setAdapter(adapterRepete)

            } else {
                editTextRepetir.isEnabled = false
                editTextRepetir.visibility = View.INVISIBLE
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home  -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
