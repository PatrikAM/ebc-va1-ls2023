package cz.mendelu.pef.va1.xmichl.golf.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.mendelu.pef.va1.xmichl.golf.model.Golfist


@Database(entities = [Golfist::class], version = 1, exportSchema = true)
abstract class GolfistsDatabase : RoomDatabase() {

    abstract fun golfistsDao(): GolfistsDao

    companion object {
        private var INSTANCE: GolfistsDatabase? = null
        fun getDatabase(context: Context): GolfistsDatabase {
            if (INSTANCE == null) {
                synchronized(GolfistsDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GolfistsDatabase::class.java, "golfists_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}