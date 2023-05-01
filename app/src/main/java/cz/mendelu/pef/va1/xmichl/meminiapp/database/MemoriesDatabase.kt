package cz.mendelu.pef.va1.xmichl.meminiapp.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
//@TypeConverters(Converters::class)

@Database(entities = [Memory::class], version = 2, exportSchema = true)
abstract class MemoriesDatabase : RoomDatabase() {

    abstract fun memoriesDao(): MemoriesDao

    companion object {
        private var INSTANCE: MemoriesDatabase? = null
        fun getDatabase(context: Context): MemoriesDatabase {
            if (INSTANCE == null) {
                synchronized(MemoriesDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MemoriesDatabase::class.java, "memories_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
