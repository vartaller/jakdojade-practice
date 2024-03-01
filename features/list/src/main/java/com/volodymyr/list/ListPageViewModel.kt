package com.volodymyr.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volodymyr.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPageViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListPageUiState())
    val uiState: StateFlow<ListPageUiState> = _uiState.asStateFlow()

    private val _effect = Channel<ListPageEffect?>()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            repository.allUserTickets.collect {
                _uiState.value = _uiState.value.copy(
                    allUserTickets = it
                )
            }
        }
        viewModelScope.launch {
            repository.allStoreTickets.collect {
                _uiState.value = _uiState.value.copy(
                    allStoreTickets = it
                )
            }
        }
    }

    fun dropTableUserTickets() {
        viewModelScope.launch {
            repository.dropTableUsersTicket()
        }
    }

    fun populateTableStoreTicket() {
        viewModelScope.launch {
            repository.populateTableStoreTicket()
        }
    }


    fun updateTabState(
        newTicketsTab: TicketsTab = _uiState.value.ticketsTab,
    ) {
//  ----- Another way to re-write state class instance
//        val tempClass = ListPageUiState(newProperty)
//        _uiState.value = tempClass
        _uiState.value = _uiState.value.copy(
            ticketsTab = newTicketsTab,
        )
    }

    fun updateTypeState(
        newTicketsType: TicketsType = _uiState.value.ticketsType,
    ) {
        _uiState.value = _uiState.value.copy(
            ticketsType = newTicketsType,
        )
    }

    fun onTicketPicked(ticketId: Int) {
        setEffect(effect = ListPageEffect.GoToTicketPurchase(ticketId))
    }

    private fun setEffect(effect: ListPageEffect) {
        viewModelScope.launch {
            _effect.send(null)
            _effect.send(effect)
        }
    }

    fun clearEffect() {
        viewModelScope.launch {
            _effect.send(null)
        }
    }
}