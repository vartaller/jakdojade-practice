package com.volodymyr.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM users_ticket ORDER BY dateStamp DESC")
    fun getUserTickets(): Flow<List<UserTicket>>

    @Query("SELECT * FROM store_ticket")
    fun getStoreTickets(): Flow<List<StoreTicket>>
    @Query("SELECT * FROM store_ticket WHERE id = :ticketId")
    fun getStoreTicket(ticketId: Int?): StoreTicket

    @Insert()
    suspend fun insertUserTicket(ticket: UserTicket)
    @Insert()
    suspend fun insertUserTickets(tickets: List<UserTicket>)
    @Insert()
    suspend fun insertStoreTicket(ticket: StoreTicket)

    @Query("DELETE FROM users_ticket")
    suspend fun dropTableUsersTicket()
    @Query("DELETE FROM store_ticket")
    suspend fun dropTableStoreTicket()
}