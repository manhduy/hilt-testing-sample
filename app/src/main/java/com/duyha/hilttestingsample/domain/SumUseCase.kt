package com.duyha.hilttestingsample.domain

import javax.inject.Inject

class SumUseCase @Inject constructor() {

    operator fun invoke(a: Int, b: Int) = a + b
}