package com.duyha.hilttestingsample.calculator

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.duyha.hilttestingsample.Event
import com.duyha.hilttestingsample.R
import com.duyha.hilttestingsample.calulator.CalculatorActivity
import com.duyha.hilttestingsample.calulator.CalculatorViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@Suppress("UNCHECKED_CAST")
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SumUseCaseActivityTest {

    @BindValue
    @JvmField
    val viewModel = mockk<CalculatorViewModel>(relaxed = true)

    private val sum = MutableLiveData<Int>()
    private val msg = MutableLiveData<Event<Int>>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        every { viewModel.sum } returns sum
        every { viewModel.msg } returns msg
    }

    @Test
    fun test_DisplaySum_WhenSumLiveDataChange() {
        //Given
        val scenario = launchActivity<CalculatorActivity>()
        //When
        sum.postValue(10)
        //Then
        onView(withId(R.id.tvSum)).check(matches(withText("10")))
    }

    @Test
    fun test_ShowMessage_WhenMessageLiveDataChange() {
        //Given
        val scenario = launchActivity<CalculatorActivity>()
        //When
        msg.postValue(Event((R.string.msg_invalid_a)))
        //Then
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.msg_invalid_a)))
        onView(withId(R.id.tvSum)).check(matches(withText("0")))
    }
}