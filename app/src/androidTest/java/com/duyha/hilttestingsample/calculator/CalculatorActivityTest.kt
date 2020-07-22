package com.duyha.hilttestingsample.calculator

import android.app.PendingIntent.getActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.duyha.hilttestingsample.Event
import com.duyha.hilttestingsample.R
import com.duyha.hilttestingsample.calulator.CalculatorActivity
import com.duyha.hilttestingsample.calulator.CalculatorViewModel
import com.duyha.hilttestingsample.calulator.CalculatorViewModelFactory
import com.duyha.hilttestingsample.di.ActivityModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@Suppress("UNCHECKED_CAST")
@HiltAndroidTest
@UninstallModules(ActivityModule::class)
@RunWith(AndroidJUnit4::class)
class CalculatorActivityTest {

    private val viewModel = mockk<CalculatorViewModel>(relaxed = true)

    @BindValue
    @JvmField
    val viewModelFactory: CalculatorViewModelFactory = object : CalculatorViewModelFactory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel as T
        }
    }

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
        msg.postValue(Event((R.string.msg_input_a)))
        //Then
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.msg_input_a)))
        onView(withId(R.id.tvSum)).check(matches(withText("0")))
    }
}