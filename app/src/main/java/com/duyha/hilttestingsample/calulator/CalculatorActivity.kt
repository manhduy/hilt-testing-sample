package com.duyha.hilttestingsample.calulator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.duyha.hilttestingsample.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_calculator.*

@AndroidEntryPoint
class CalculatorActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        viewModel.sum.observe(this, Observer {
            tvSum.text = it.toString()
        })
        viewModel.msg.observe(this, Observer {
            it.getContentIfNotHandled()?.let { msg ->
                showMessage(msg)
            }
        })

    }

    private fun showMessage(@StringRes msgId: Int) {
        val msg = getString(msgId)
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        Snackbar.make(rootView, msgId, Snackbar.LENGTH_LONG).show()

    }

    fun onSumClick(view: View) {
        hideKeyboard()
        viewModel.onSumClick(edtA.text.toString().trim(), edtB.text.toString().trim())
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}