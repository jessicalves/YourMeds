package com.example.yourmeds.model

import android.content.Context

object DataStore {
    var remedyes: MutableList<Remedy> = arrayListOf()
        private set

    private var mvContext: Context? = null

    fun setContext(value: Context) {
        mvContext = value
    }

    fun getRemedy(position: Int): Remedy {
        return remedyes.get(position)
    }

    fun addRemedy(remedy: Remedy) {
        remedyes.add(remedy)
    }

    fun editRemedy(remedy: Remedy, position: Int) {
        remedyes.set(position, remedy)
    }

    fun removeRemedy(position: Int) {
        remedyes.removeAt(position)
    }
}