package br.com.joaogoes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.joaogoes.database.dao.RevisionItemDAO
import br.com.joaogoes.database.entity.RevisionItemEntity

const val DATABASE_NAME = "Storage"
const val DATABASE_VERSION = 1

@Database(entities = [RevisionItemEntity::class], version = DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun revisionItemDao(): RevisionItemDAO

    companion object {
        fun createDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}