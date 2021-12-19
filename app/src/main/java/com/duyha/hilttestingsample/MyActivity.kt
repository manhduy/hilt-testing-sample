package com.duyha.hilttestingsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@AndroidEntryPoint
class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity)
    }
}

@AndroidEntryPoint
class MyFragment1 : Fragment() {
    @Inject lateinit var myService: MyService
}

@AndroidEntryPoint
class MyFragment2 : Fragment() {
    @Inject lateinit var myService: MyService
}

@ActivityScoped
class MyService @Inject constructor() {
    init {
        Log.d("MyService", "Create new instance of MyService")
    }
}