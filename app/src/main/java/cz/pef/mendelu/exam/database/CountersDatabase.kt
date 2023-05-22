package cz.pef.mendelu.exam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.pef.mendelu.exam.model.Counter

@Database(entities = [Counter::class], version = 1, exportSchema = true)
abstract class CountersDatabase : RoomDatabase() {

    abstract fun tasksDao(): CountersDao

    companion object {
        private var INSTANCE: CountersDatabase? = null
        fun getDatabase(context: Context): CountersDatabase {
            if (INSTANCE == null) {
                synchronized(CountersDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CountersDatabase::class.java, "counters_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}