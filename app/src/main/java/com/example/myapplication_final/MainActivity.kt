package com.example.myapplication_final

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
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

        val calendarButton = findViewById<Button>(R.id.calendar_button)
        calendarButton.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
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

    private fun learnMore(query:String){
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply{
            putExtra(SearchManager.QUERY, query)
        }
        try{
            startActivity(intent)
        } catch (e:ActivityNotFoundException) {
            Snackbar.make(
                findViewById(R.id.linear_layout),
                R.string.failed_web_search,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
