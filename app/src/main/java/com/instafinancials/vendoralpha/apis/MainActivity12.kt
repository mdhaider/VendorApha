package com.instafinancials.vendoralpha.apis

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.instafinancials.vendoralpha.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity12 : AppCompatActivity() {
    private var weatherData: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)    
        setContentView(R.layout.activity_main12)
        weatherData = findViewById(R.id.textView)    
        findViewById<View>(R.id.button).setOnClickListener { getCurrentData() }
    }    
    internal fun getCurrentData() {    
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)    
                .addConverterFactory(GsonConverterFactory.create())
                .build()    
        val service = retrofit.create(WeatherService::class.java)    
        val call = service.getGstData(lat)
        call.enqueue(object : Callback<GstResponse> {
            override fun onResponse(call: Call<GstResponse>, response: Response<GstResponse>) {
                if (response.code() == 200) {    
                    val weatherResponse = response.body()!!

                    Log.d("tag", weatherResponse.toString())
    
                    val stringBuilder = "Country: " +    
                          weatherResponse.gSTInformationAndCompliance?.gSTRegistrationDetails?.constitution +
                            "\n" +    
                            "Temperature: " +
                            weatherResponse.gSTInformationAndCompliance?.gSTRegistrationDetails?.registeredState
    
                    weatherData!!.text = stringBuilder    
                }    
            }    
    
            override fun onFailure(call: Call<GstResponse>, t: Throwable) {
                weatherData!!.text = t.message    
            }    
        })    
    }    
    companion object {
        var BaseUrl = "https://apps.instafinancials.com/"
        var lat = "01AAACA6990Q1ZC"
    }    
}    