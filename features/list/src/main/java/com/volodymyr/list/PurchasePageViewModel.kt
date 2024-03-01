package com.volodymyr.list

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volodymyr.data.Repository
import com.volodymyr.data.StoreTicket
import com.volodymyr.data.UserTicket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PurchasePageViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(PurchasePageUiState())
    val uiState: StateFlow<PurchasePageUiState> = _uiState.asStateFlow()

    private val _effect = Channel<PurchasePageEffect?>()
    val effect = _effect.receiveAsFlow()

    init {
        getStoreTicketId()
        startTimer(
            timeDeadline = _uiState.value.timeDeadline,
            timeCounter = _uiState.value.timeCounter
        )
    }

    private fun getStoreTicketId() {
        val ticketId = savedStateHandle.get<Int>("ticketId")
        ticketId?.let { getStoreTicket(ticketId = it) }
    }

    private fun getStoreTicket(ticketId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val ticket = repository.getStoreTicket(ticketId = ticketId)
            _uiState.value = _uiState.value.copy(
                ticket = ticket,
            )
        }
    }

    private fun startTimer(timeDeadline: Date, timeCounter: Int) {
        val currentTime = Calendar.getInstance().timeInMillis
        val timeDifference = timeDeadline.time - currentTime
        var countDownTimer = object : CountDownTimer(timeDifference, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _uiState.value = _uiState.value.copy(
                    timeCounter = _uiState.value.timeCounter - 1,
                )
            }

            override fun onFinish() {
                setEffect(effect = PurchasePageEffect.GoBack(toReturn = true))
            }
        }

        countDownTimer.start()
    }

    fun insertUserTickets(storeTicket: StoreTicket, amount: Int) {
        val userTicketsList = mutableListOf<UserTicket>()

        repeat(amount) {
            val userTicket = UserTicket(
                id = 0,
                type = storeTicket.type,
                provider = storeTicket.provider,
                scope = storeTicket.scope,
                duration = storeTicket.duration,
                unit = storeTicket.unit,
                price = storeTicket.price,
                dateStamp = Date(),
            )

            userTicketsList.add(userTicket)
        }
        viewModelScope.launch {
            repository.insertUserTickets(userTicketsList)
        }
    }


    fun updateTicketAmount(changeTicketAmountTo: Int = _uiState.value.ticketAmount) {
        _uiState.value = _uiState.value.copy(
            ticketAmount = _uiState.value.ticketAmount + changeTicketAmountTo
        )
    }

    private fun setEffect(effect: PurchasePageEffect) {
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