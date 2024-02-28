package com.volodymyr.data

import android.content.Context
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
@Database(entities = [UserTicket::class], version = 1, exportSchema = false)
public abstract class UserTicketDatabase : RoomDatabase() {

    abstract fun usersTicketDao(): UserTicketDao

    private class UserTicketDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.usersTicketDao())
                }
            }
        }

        suspend fun populateDatabase(usersTicketDao: UserTicketDao) {
            usersTicketDao.deleteAll()
            val samplesList = sampleData()
            println("samplesList = $samplesList")
            samplesList.forEach {
                usersTicketDao.insert(it)
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: UserTicketDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserTicketDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserTicketDatabase::class.java,
                    "my_app_database",
                ).addCallback(UserTicketDatabaseCallback(scope)).build()
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