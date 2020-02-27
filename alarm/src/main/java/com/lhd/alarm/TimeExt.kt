package com.lhd.alarm

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val timeStampPerDay = 86400000L
val normalDateFormat = "dd-MM-yyyy HH:mm"
val timeDrink = "HH:mm"

fun Date.toFormat(stringFormat: String= normalDateFormat, locale: Locale = Locale.getDefault()): String {
    val format = SimpleDateFormat(stringFormat, locale)
    return format.format(this)
}
fun Date.toFormatTime(stringFormat: String= timeDrink, locale: Locale = Locale.getDefault()): String {
    val format = SimpleDateFormat(stringFormat, locale)
    return format.format(this)
}

fun String.toDate(stringFormat: String= normalDateFormat, locale: Locale = Locale.getDefault()): Date {
    val format = SimpleDateFormat(stringFormat, locale)
    var res = Date()
    try{
        res = format.parse(this)!!
    }catch (e: ParseException){

    }
    return res
}

fun Long.toStringDateFormat(stringFormat: String= normalDateFormat, locale: Locale = Locale.getDefault(), notUpcaseAll:Boolean=true): String {
    val timeStamp = this
    val format = SimpleDateFormat(stringFormat, locale)
    val date = Calendar.getInstance().apply {
        timeInMillis = timeStamp
    }.time?:Date()
    return format.format(date)
}

fun countLengthBetween(fromDate:Date,toDate:Date): Int {
    return ((toDate.time - fromDate.time)/ timeStampPerDay).toInt()
}