package com.volodymyr.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListPageViewModel @Inject constructor (
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListPageUiState())
    val uiState: StateFlow<ListPageUiState> = _uiState.asStateFlow()

    fun updateState(newProperty: TicketsTab) {
//  Another way to re-write state class instance
//        val tempClass = ListPageUiState(newProperty)
//        _uiState.value = tempClass
        _uiState.value = _uiState.value.copy(currentTicketsTab = newProperty)
    }
}
