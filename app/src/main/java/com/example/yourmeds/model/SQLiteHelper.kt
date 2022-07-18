package com.example.yourmeds.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "remedy.db"
        private const val TBL_REMEDY = "tbl_remedy"
        private const val ID = "id"
        private const val NAME = "nome"
        private const val DOSE = "dose"
        private const val COLOR = "cor"
        private const val DAYS = "dias"
        private const val PERIOD = "period"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}