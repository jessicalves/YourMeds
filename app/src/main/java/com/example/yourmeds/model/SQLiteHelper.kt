package com.example.yourmeds.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "remedy.db"
        private const val TBL_REMEDY = "tbl_remedy"
        private const val ID = "id"
        private const val NAME = "nome"
        private const val DOSE = "dose"
        private const val DATE = "date"
        private const val COLOR = "cor"
        private const val DAYS = "dias"
        private const val PERIOD = "period"

        private const val sqlCreateRemedy =
            ("CREATE TABLE IF NOT EXISTS $TBL_REMEDY($ID INTEGER PRIMARY KEY AUTOINCREMENT,$NAME TEXT,$DOSE TEXT,$DATE TEXT,$COLOR TEXT,$DAYS INTEGER, $PERIOD TEXT)")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val db = db ?: return

        db.beginTransaction()
        try {

            db?.execSQL(sqlCreateRemedy)
            db.setTransactionSuccessful()
        } finally {

            db.endTransaction()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_REMEDY")
        onCreate(db)
    }

    fun insertRemedy(med: RemedyModel) : Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(ID, med.id)
        contentValues.put(NAME, med.nome)
        contentValues.put(DOSE, med.dose)
        contentValues.put(DATE, med.date)
        contentValues.put(COLOR, med.cor)
        contentValues.put(DAYS, med.days)
        contentValues.put(PERIOD, med.periodo)

        val success = db.insert(TBL_REMEDY, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllRemedies(): MutableList<RemedyModel> {
        var remedies = mutableListOf<RemedyModel>()
        val db = readableDatabase

        val cursor = db.query(
            TBL_REMEDY,
            null,
            null,
            null,
            null,
            null,
            NAME
        )
        with(cursor) {

            while (moveToNext()) {

                val id = getInt(getColumnIndex(ID))
                val nome = getString(getColumnIndex(NAME))
//                val dose = getString(getColumnIndex(DOSE))
                val data = getString(getColumnIndex(DATE))
//                val cor = getString(getColumnIndex(COLOR))
//                val dias = getInt(getColumnIndex(DAYS))
//                val periodo = getString(getColumnIndex(PERIOD))

                val remedy = RemedyModel(id, nome, data)
                remedies.add(remedy)

            }
        }
        return remedies
    }
}