package com.volodymyr.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class RepositoryImpl(private val dao: Dao): Repository {

    override val allUserTickets: Flow<List<UserTicket>> = dao.getUserTickets()
    override val allStoreTickets: Flow<List<StoreTicket>> = dao.getStoreTickets()

    @WorkerThread
    override suspend fun getStoreTicket(ticketId: Int?): StoreTicket {
        return dao.getStoreTicket(ticketId = ticketId)
    }
//    @WorkerThread
//    override suspend fun insertUserTicket(ticket: UserTicket) {
//        dao.insertUserTicket(ticket)
//    }
    @WorkerThread
    override suspend fun insertUserTickets(ticketsList: List<UserTicket>) {
        dao.insertUserTickets(ticketsList)
    }
    @WorkerThread
    override suspend fun dropTableUsersTicket() {
        dao.dropTableUsersTicket()
    }
    @WorkerThread
    override suspend fun dropTableStoreTicket() {
        dao.dropTableStoreTicket()
    }
    @WorkerThread
    override suspend fun populateTableStoreTicket() {
        val sampleStoreTicketsList = sampleStoreTicketsData()
        sampleStoreTicketsList.forEach {
            dao.insertStoreTicket(it)
        }
    }
}

interface Repository {
    suspend fun getStoreTicket(ticketId: Int?):StoreTicket
//    suspend fun insertUserTicket(ticket: UserTicket)
    suspend fun insertUserTickets(ticketsList: List<UserTicket>)
    suspend fun dropTableUsersTicket()
    suspend fun dropTableStoreTicket()
    suspend fun populateTableStoreTicket()

    val allUserTickets: Flow<List<UserTicket>>
    val allStoreTickets: Flow<List<StoreTicket>>
}
