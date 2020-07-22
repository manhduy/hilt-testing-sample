package com.duyha.hilttestingsample.calulator

import javax.inject.Inject

class Calculator @Inject constructor() {

    fun sum(a: Int, b: Int) = a + b
}