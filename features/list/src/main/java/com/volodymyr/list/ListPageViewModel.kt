package com.volodymyr.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volodymyr.data.Repository
import com.volodymyr.data.UserTicket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPageViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListPageUiState())
    val uiState: StateFlow<ListPageUiState> = _uiState.asStateFlow()

//    val allUserTickets: LiveData<List<UserTicket>> = repository.allUserTickets.asLiveData()

//    val userTicketsList: List<UserTicket> = mutableListOf()

    init {
        viewModelScope.launch {
            repository.allUserTickets.collect {
                _uiState.value = _uiState.value.copy(
                    allUserTickets = it
                )
            }
        }
    }

    fun insertUserTicket(userTicket: UserTicket) = viewModelScope.launch {
        repository.insertUserTicket(userTicket)
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
}