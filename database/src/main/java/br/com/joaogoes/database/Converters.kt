package br.com.joaogoes.database

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun dateToLong(calendar: Calendar?): Long? = calendar?.timeInMillis

    @TypeConverter
    fun longToDate(long: Long?): Calendar? = long?.let {
        Calendar.getInstance().apply { timeInMillis = long }
    }
}