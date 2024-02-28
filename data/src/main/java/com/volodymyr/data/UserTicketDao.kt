package com.volodymyr.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTicketDao {

    @Query("SELECT * FROM users_ticket ORDER BY dateStamp DESC")
    fun getUserTickets(): Flow<List<UserTicket>>

    @Insert()
    suspend fun insert(ticket: UserTicket)

    @Query("DELETE FROM users_ticket")
    suspend fun deleteAll()
}