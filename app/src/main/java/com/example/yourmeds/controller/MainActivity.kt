package com.example.yourmeds.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmeds.R
import com.example.yourmeds.model.RemedyModel
import com.example.yourmeds.model.SQLiteHelper
import com.example.yourmeds.view.RemedyAdapter

open class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var adapter: RemedyAdapter? = null
    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        sqLiteHelper = SQLiteHelper(this)
        recyclerView = findViewById(R.id.recycleView)
        initRecyclerView()
        getRemedy()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RemedyAdapter()
        recyclerView.adapter = adapter
    }

    private fun getRemedy() {
        val medList = sqLiteHelper.getAllRemedies()
        adapter?.addItems(medList as ArrayList<RemedyModel>)
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search -> {
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.plus -> {
            val mIntent = Intent(this, ChildActivity::class.java)
            startActivity(mIntent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}

