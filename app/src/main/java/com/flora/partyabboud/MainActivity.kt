package com.flora.partyabboud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnClick.setOnClickListener()
        {

            CoroutineScope(IO).launch {
                fakeResultApi()
            }
        }
    }

    private suspend fun fakeResultApi() {
        val result = getResult1FromApi()
        setTextResultOnMainThread(result)
    }
    private suspend fun setTextResultOnMainThread(result:String) {
        withContext(Main) {
            txt.setText(result)
        }
    }

    private suspend fun getResult1FromApi(): String {
        logThread("get method result")
        delay(1000)
        return "result1"
    }

    private fun logThread(methodName: String) {
        println("debug: $methodName : ${Thread.currentThread().name}")
    }


}