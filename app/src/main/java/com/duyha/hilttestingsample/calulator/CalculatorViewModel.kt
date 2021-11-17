package com.duyha.hilttestingsample.calulator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duyha.hilttestingsample.Calculator
import com.duyha.hilttestingsample.Event
import com.duyha.hilttestingsample.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculator: Calculator
) : ViewModel() {

    private val _sum = MutableLiveData<Int>().apply { value = 0 }
    val sum: LiveData<Int>
        get() = _sum

    private val _msg = MutableLiveData<Event<Int>>()
    val msg: LiveData<Event<Int>>
        get() = _msg

    fun onSumClick(textA: String, textB: String) {
        if (textA.isEmpty()) {
            _sum.value = 0
            _msg.value =
                Event(R.string.msg_input_a)
            return
        }

        if (textB.isEmpty()) {
            _sum.value = 0
            _msg.value =
                Event(R.string.msg_input_b)
            return
        }

        _sum.value = calculator.sum(textA.toInt(), textB.toInt())
    }
}