package com.example.quotes_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes_app.network.Quotes
import com.example.quotes_app.network.QuotesApi
import kotlinx.coroutines.launch

enum class QuotesApiStatus {LOADING, ERROR, DONE}

class QuotesViewModel : ViewModel() {
    private val _status = MutableLiveData<QuotesApiStatus>()
    val status: LiveData<QuotesApiStatus> = _status

    private val _quotes = MutableLiveData<List<Quotes>>()
    val quotes: MutableLiveData<List<Quotes>> = _quotes

    private val _quote = MutableLiveData<Quotes>()
    val quote: LiveData<Quotes> = _quote

    fun getQuotesList() {
        viewModelScope.launch {
            _status.value = QuotesApiStatus.LOADING
            try {
                _quotes.value = QuotesApi.retrofitServiceApi.get()
                _status.value = QuotesApiStatus.DONE
            } catch (e: Exception) {
                _quotes.value = listOf()
                _status.value = QuotesApiStatus.ERROR
            }
        }
    }
    fun onQuotesClicked(quotes: Quotes) {
        _quote.value = quotes
    }
}
