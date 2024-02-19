package com.volodymyr.ticket

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TicketPageViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(TicketPageUiState())
    val uiState: StateFlow<TicketPageUiState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        val inputData = TicketDataValues()
        val outputData = mutableListOf<TicketDataModel>()
        TicketDataFieldNames.values().forEach {
            outputData.add(
                TicketDataModel(
                    title = it,
                    value = when (it) {
                        TicketDataFieldNames.VALID_FROM -> inputData.validFrom
                        TicketDataFieldNames.VALID_TO -> inputData.validTo
                        TicketDataFieldNames.PHONE -> inputData.phone
                        TicketDataFieldNames.VEHICLE -> inputData.vehicle
                        TicketDataFieldNames.PRICE -> inputData.price
                        TicketDataFieldNames.PROVIDER -> inputData.provider
                        TicketDataFieldNames.CODE -> inputData.code
                    }
                )
            )
        }
        _uiState.value = _uiState.value.copy(
            ticketValues = outputData,
            ticketTime = inputData.time,
            ticketScope = inputData.scope,
            ticketTimeUnit = inputData.unit
        )
    }
}