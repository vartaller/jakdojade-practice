package com.volodymyr.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volodymyr.data.Repository
import com.volodymyr.data.UserTicket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TicketPageViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(TicketPageUiState())
    val uiState: StateFlow<TicketPageUiState> = _uiState.asStateFlow()

    init {
        getUserTicketId()
    }

    private fun getUserTicketId() {
        val ticketId = savedStateHandle.get<Int>("ticketId")
        ticketId?.let { getUserTicket(ticketId = it) }
    }

    private fun getUserTicket(ticketId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val ticket = repository.getUserTicket(ticketId = ticketId)
            val dateFrom = getDateFrom(ticket)
            val dateTo = getDateTo(ticket)
            _uiState.value = _uiState.value.copy(
                ticket = ticket,
                dateFrom = SimpleDateFormat(
                    "dd.MM.yyyy HH:mm:ss",
                    Locale.getDefault()
                ).format(dateFrom),
                dateTo = SimpleDateFormat(
                    "dd.MM.yyyy HH:mm:ss",
                    Locale.getDefault()
                ).format(dateTo),
                isActive = dateTo.after(Date())
            )
        }
    }

    private fun getDateFrom(ticket: UserTicket): Date {
        return Date(ticket.dateStamp.time)
    }

    private fun getDateTo(ticket: UserTicket): Date {
        val calendar = Calendar.getInstance()
        calendar.time = Date(ticket.dateStamp.time)
        when (ticket.unit.type) {
            "minut", "minut or single trip" -> calendar.add(
                Calendar.MINUTE,
                ticket.duration.type.toInt()
            )

            "hour", "hour (I)", "hour (I+II+III)", "day (I)" -> calendar.add(
                Calendar.HOUR,
                ticket.duration.type.toInt()
            )

            "day (I+II+III)" -> calendar.add(Calendar.HOUR, ticket.duration.type.toInt() * 24)
            "single trip, up to 20 people", "family ticket" -> calendar.add(Calendar.HOUR, 48)
            else -> Unit
        }
        return calendar.time
    }
}