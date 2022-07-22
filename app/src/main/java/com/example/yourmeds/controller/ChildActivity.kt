package com.example.yourmeds.controller

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmeds.R
import com.example.yourmeds.model.RemedyModel
import com.example.yourmeds.model.SQLiteHelper
import com.example.yourmeds.view.RemedyAdapter
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class ChildActivity : AppCompatActivity() {

    private lateinit var autoCor: AutoCompleteTextView
    private lateinit var editTextRepetir: TextInputLayout
    private lateinit var editTextCor: TextInputLayout
    private lateinit var swLembrete: SwitchMaterial
    private lateinit var autoRepetir: AutoCompleteTextView
    private lateinit var btnSalvar: Button
    private lateinit var editTextNome: TextInputEditText
    private lateinit var editTextDose: TextInputEditText
    private lateinit var editTextDias: TextInputEditText
    private lateinit var btnData: Button
    private lateinit var tvDate: TextView
    private lateinit var recyclerView: RecyclerView
    private var adapter: RemedyAdapter? = null

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)
        initView()
        sqLiteHelper = SQLiteHelper(this)
        btnSalvar.setOnClickListener {
            addRemedy()
            initRecyclerView()
            getRemedy()
            finish()
        }
        supportActionBar!!.setTitle("Adicionar")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)

        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        btnData.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { view, myear, mmonth, mdayOfMonth ->
                tvDate.setText("" + mdayOfMonth + "/" + "0" + mmonth + "/" + myear)
            }, year, month, day)
            datePickerDialog.show()
        }

        val itemsCor = listOf(
            "Nenhuma",
            "Vermelho",
            "Azul",
            "Laranja",
            "Verde",
            "Amarelo",
            "Preto",
            "Rosa",
            "Roxo"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsCor)
        autoCor.setAdapter(adapter)
        autoCor.setText(autoCor.adapter.getItem(0).toString(), false)

        swLembrete.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editTextRepetir.isEnabled = true
                editTextRepetir.visibility = View.VISIBLE
                val itemsRepetir = listOf(
                    "A cada 15 minutos",
                    "A cada 30 minutos",
                    "A cada 1 hora",
                    "A cada 2 horas",
                    "A cada 3 horas",
                    "A cara 4 horas",
                    "A cada 5 horas",
                    "A cada 6  horas",
                    "A cada 8 horas",
                    "A cada 12 horas",
                    "A cada 24 horas",
                    "A cada 2 dias",
                    "A cada 1 semana",
                    "A cada 15 dias",
                    "A cada 1 mês"
                )
                val adapterRepete =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsRepetir)
                autoRepetir.setAdapter(adapterRepete)
                autoRepetir.setText(autoRepetir.adapter.getItem(0).toString(), false)

            } else {
                editTextRepetir.isEnabled = false
                editTextRepetir.visibility = View.INVISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RemedyAdapter()
        recyclerView.adapter = adapter
    }

    private fun getRemedy() {
        val medList = sqLiteHelper.getAllRemedies()
        adapter?.addItems(medList)
    }

    private fun addRemedy() {
        val nome = editTextNome.text.toString()
        val dose = editTextDose.text.toString()
        val cor = autoCor.text.toString()
        val data = tvDate.text.toString()
        val dias: Int
        if (editTextDias.text.toString().isEmpty()) dias = 0 else dias =
            editTextDias.text.toString().toInt()
        val period = autoRepetir.text.toString()

        if (nome.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha os campos Nome e Data.", Toast.LENGTH_LONG)
                .show()
            return
        } else {
            val med = RemedyModel(
                nome = nome,
                dose = dose,
                date = data,
                cor = cor,
                days = dias,
                periodo = period
            )
            val status = sqLiteHelper.insertRemedy(med)

            if (status > -1) {
                Toast.makeText(this, "Adicionado com sucesso.", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "Não foi adicionado.", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun cleanEditText() {
        editTextNome.setText("")
        editTextDose.setText("")
        editTextDias.setText("")
        tvDate.setText("Data de início")
        editTextNome.requestFocus()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun initView() {
        setSupportActionBar(findViewById(R.id.toolbar))
        autoCor = findViewById(R.id.autoCor)
        editTextRepetir = findViewById(R.id.editTextRepetir)
        editTextCor = findViewById(R.id.editTextCor)
        swLembrete = findViewById(R.id.swLembrete)
        autoRepetir = findViewById(R.id.autoRepetir)
        btnSalvar = findViewById(R.id.btnSalvar)
        editTextNome = findViewById(R.id.editTextNome)
        editTextDose = findViewById(R.id.editTextDose)
        editTextDias = findViewById(R.id.editTextDias)
        btnData = findViewById(R.id.btnData)
        tvDate = findViewById(R.id.tvDate)
    }
}
