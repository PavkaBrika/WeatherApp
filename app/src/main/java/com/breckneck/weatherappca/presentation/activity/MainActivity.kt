package com.breckneck.weatherappca.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.breckneck.weatherappca.R
import com.breckneck.weatherappca.usecase.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope

class MainActivity : AppCompatActivity() {

    private val getWeatherUseCase: GetWeatherUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSetCity : Button = findViewById(R.id.buttonSetCity)
        val cityEditText : EditText = findViewById(R.id.cityEditText)

        val cityTextView : TextView = findViewById(R.id.cityTextView)
        val degreesTextView: TextView = findViewById(R.id.degreesTextView)
        val feelsLikeTextView: TextView = findViewById(R.id.feelslikeTextView)
        val weatherTextView: TextView = findViewById(R.id.weatherTextView)



        buttonSetCity.setOnClickListener {
            activityScope().lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val weather = getWeatherUseCase.execute(cityEditText.text.toString())
                Log.e("TAG", "Success")
                Log.e("TAG", weather.city)
                Log.e("TAG", weather.weather)
                Log.e("TAG", weather.degrees.toString())
                Log.e("TAG", weather.feelsLike.toString())
                launch(Dispatchers.Main) {
                    cityTextView.text = "${resources.getString(R.string.city)} ${weather.city}"
                    degreesTextView.text = "${resources.getString(R.string.temperature)} ${weather.degrees} ${resources.getString(R.string.metric)}"
                    feelsLikeTextView.text = "${resources.getString(R.string.feelslike)} ${weather.feelsLike} ${resources.getString(R.string.metric)}"
                    weatherTextView.text = "${resources.getString(R.string.weather)} ${weather.weather}"
                    TODO("ADD EXCEPTION HANDLING")
                }
            }
//            val weather = getWeatherUseCase.execute("eaf")
//            Log.e("TAG", "Success")
//            Log.e("TAG", weather.city)
//            Log.e("TAG", weather.weather)
//            Log.e("TAG", weather.degrees.toString())
//            Log.e("TAG", weather.feelsLike.toString())
        }
    }
}