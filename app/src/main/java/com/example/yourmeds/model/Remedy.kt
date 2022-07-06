package com.example.yourmeds.model

import android.text.format.DateFormat

class Remedy(
    var name: String,
    var dose: String,
    var cor: String,
    var date: DateFormat,
    var days: Int,
    var repetir: String
) {}