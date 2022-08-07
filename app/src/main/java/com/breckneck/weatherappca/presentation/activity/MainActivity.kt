package com.breckneck.weatherappca.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.breckneck.weatherappca.R
import com.breckneck.weatherappca.usecase.GetWeatherDataBaseUseCase
import com.breckneck.weatherappca.usecase.GetWeatherUseCase
import com.breckneck.weatherappca.usecase.SaveWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope

class MainActivity : AppCompatActivity() {

    private val getWeatherUseCase: GetWeatherUseCase by inject()
    private val getWeatherDataBaseUseCase: GetWeatherDataBaseUseCase by inject()
    private val saveWeatherUseCase: SaveWeatherUseCase by inject()

    private val SP = "sp"
    private val open = "open"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSetCity : Button = findViewById(R.id.buttonSetCity)
        val cityEditText : EditText = findViewById(R.id.cityEditText)

        val cityTextView : TextView = findViewById(R.id.cityTextView)
        val degreesTextView: TextView = findViewById(R.id.degreesTextView)
        val feelsLikeTextView: TextView = findViewById(R.id.feelslikeTextView)
        val weatherTextView: TextView = findViewById(R.id.weatherTextView)

        val sp = applicationContext.getSharedPreferences(SP, MODE_PRIVATE)
        var isOpened = sp.getBoolean(open, false)

        if (isOpened == true) {
            activityScope().lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val weather = getWeatherDataBaseUseCase.execute()
                launch(Dispatchers.Main) {
                    cityTextView.text = "${resources.getString(R.string.city)} ${weather.city}"
                    degreesTextView.text = "${resources.getString(R.string.temperature)} ${weather.degrees} ${resources.getString(R.string.metric)}"
                    feelsLikeTextView.text = "${resources.getString(R.string.feelslike)} ${weather.feelsLike} ${resources.getString(R.string.metric)}"
                    weatherTextView.text = "${resources.getString(R.string.weather)} ${weather.weather}"
                }
            }
        }





        buttonSetCity.setOnClickListener {
            activityScope().lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val weather = getWeatherUseCase.execute(cityEditText.text.toString())
                if (weather.data != null) {
                    saveWeatherUseCase.execute(weather = weather.data!!)
                    sp.edit().putBoolean(open, true).apply()
                    Log.e("TAG", "Success")
                    Log.e("TAG", weather.data!!.city)
                    Log.e("TAG", weather.data!!.weather)
                    Log.e("TAG", weather.data!!.degrees.toString())
                    Log.e("TAG", weather.data!!.feelsLike.toString())
                    launch(Dispatchers.Main) {
                        cityTextView.text = "${resources.getString(R.string.city)} ${weather.data!!.city}"
                        degreesTextView.text = "${resources.getString(R.string.temperature)} ${weather.data!!.degrees} ${resources.getString(R.string.metric)}"
                        feelsLikeTextView.text = "${resources.getString(R.string.feelslike)} ${weather.data!!.feelsLike} ${resources.getString(R.string.metric)}"
                        weatherTextView.text = "${resources.getString(R.string.weather)} ${weather.data!!.weather}"
                    }
                } else {
                    cityTextView.text = weather.message
                    degreesTextView.text = ""
                    feelsLikeTextView.text = ""
                    weatherTextView.text = ""
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