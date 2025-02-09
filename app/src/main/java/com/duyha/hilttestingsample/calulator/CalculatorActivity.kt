package com.duyha.hilttestingsample.calulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duyha.hilttestingsample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}

@Preview
@Composable
fun MainView(
    viewModel: CalculatorViewModel = viewModel()
) {
    var textA by remember { mutableStateOf("") }
    var textB by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        TextField(
            value = textA,
            label = {
                Text(
                    text = "A",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            onValueChange =  { text ->
                textA = text
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
        TextField(
            modifier = Modifier.padding(top = 16.dp),
            value = textB,
            label = {
                Text(
                    text = "B",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            onValueChange =  { text ->
                textB = text
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
        Button(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            onClick = {
                viewModel.onSumClick(textA, textB)
                keyboardController?.hide()
            }
        ) {
            Text(
                text = "SUM",
                style = MaterialTheme.typography.titleLarge
            )
        }
        SumResultView(uiState)
    }
}

@Preview
@Composable
fun SumResultView(uiState: CalculatorUiState = CalculatorUiState.Success(10)) {
    when (uiState) {
        is CalculatorUiState.Initial -> {
            Spacer(Modifier.width(4.dp))
        }
        CalculatorUiState.InvalidTextA ->
            Text(
                text = stringResource(R.string.msg_invalid_a),
                style = MaterialTheme.typography.titleSmall
            )
        CalculatorUiState.InvalidTextB ->
            Text(
                text = stringResource(R.string.msg_invalid_b),
                style = MaterialTheme.typography.titleSmall
            )
        is CalculatorUiState.Success ->
            Text(
                text = uiState.sum.toString(),
                style = MaterialTheme.typography.headlineLarge
            )
    }
}
