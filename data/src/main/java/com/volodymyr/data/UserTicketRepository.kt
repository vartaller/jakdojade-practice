package com.volodymyr.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class UserTicketRepositoryImpl(private val usersTicketDao: UserTicketDao): UserTicketRepository {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    override val allUserTickets: Flow<List<UserTicket>> = usersTicketDao.getUserTickets()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    override suspend fun insert(ticket: UserTicket) {
        usersTicketDao.insert(ticket)
    }
}

interface UserTicketRepository {
    suspend fun insert(ticket: UserTicket)
    val allUserTickets: Flow<List<UserTicket>>
}
