package com.example.yourmeds.model

import android.text.format.DateFormat
import java.util.*

class RemedyModel(
    var id: Long = 0,
    var nome: String = "",
    var dose: String = "",
    var cor: String = "",
    var date: String = "",
    var days: Int = 0,
    var periodo: String = ""
) {
}