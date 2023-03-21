package com.example.myapplication_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.boredapi.com/api/"
class MainActivity : AppCompatActivity() {
    private lateinit var textView : TextView
    val TAG = "Activity Main"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //https://www.boredapi.com/api/activity

        getData()

    }

    private fun getData() {
        val retroFitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(APInterface::class.java)

        val retrofitReturnData = retroFitBuilder.getData()

        retrofitReturnData.enqueue(object : Callback<BoredData?> {
            override fun onResponse(call: Call<BoredData?>, response: Response<BoredData?>) {
                val responseBody = response.body()!!

                textView = findViewById(R.id.textView)
                textView.append(responseBody.activity)
            }

            override fun onFailure(call: Call<BoredData?>, t: Throwable) {
                Log.d(TAG,"onFailure: " + t.message)
            }
        })
    }
}