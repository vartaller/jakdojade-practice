package com.volodymyr.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Date

// Annotates class to be a Room Database with a table (entity) of the Word class
@TypeConverters(Converters::class)
@Database(
    entities = [UserTicket::class, StoreTicket::class],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    version = 2, exportSchema = true
)
public abstract class DatabaseApp : RoomDatabase() {

    abstract fun dao(): Dao

    private class DatabaseAppCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.dao())
                }
            }
        }

        suspend fun populateDatabase(dao: Dao) {
            val sampleStoreTicketsList = sampleStoreTicketsData()
            sampleStoreTicketsList.forEach {
                dao.insertStoreTicket(it)
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: DatabaseApp? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): DatabaseApp {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseApp::class.java,
                    "my_app_database",
                ).addCallback(DatabaseAppCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}