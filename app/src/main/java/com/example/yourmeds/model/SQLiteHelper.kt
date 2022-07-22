package com.example.yourmeds.model

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

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

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent =
            ("CREATE TABLE IF NOT EXISTS $TBL_REMEDY($ID INTEGER PRIMARY KEY,$NAME TEXT,$DOSE TEXT,$DATE TEXT,$COLOR TEXT,$DAYS INTEGER, $PERIOD TEXT)")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_REMEDY")
        onCreate(db)
    }

    fun insertRemedy(med: RemedyModel): Long {
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
    fun getAllRemedies(): ArrayList<RemedyModel> {
        val medList: ArrayList<RemedyModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_REMEDY"
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var nome: String
        var data: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nome = cursor.getString(cursor.getColumnIndex("nome"))
                data = cursor.getString(cursor.getColumnIndex("date"))
                val med = RemedyModel(id = id, nome = nome, date = data)
                medList.add(med)
            } while (cursor.moveToNext())
        }
        return medList
    }
}