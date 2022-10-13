package com.breckneck.weatherappca.presentation.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.breckneck.weatherappca.R
import com.breckneck.weatherappca.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()

    private val SP = "sp"
    private val open = "open"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", "Activity Created")

        val buttonSetCity : Button = findViewById(R.id.buttonSetCity)
        val cityEditText : EditText = findViewById(R.id.cityEditText)

        val cityTextView : TextView = findViewById(R.id.cityTextView)
        val degreesTextView: TextView = findViewById(R.id.degreesTextView)
        val feelsLikeTextView: TextView = findViewById(R.id.feelslikeTextView)
        val weatherTextView: TextView = findViewById(R.id.weatherTextView)
        val loadingTextView: TextView = findViewById(R.id.loadingTextView)
        val refreshButton: Button = findViewById(R.id.refreshButton)

        var city = ""

        val sp = applicationContext.getSharedPreferences(SP, MODE_PRIVATE)
        var isOpened = sp.getBoolean(open, false)

        if (isOpened == true) {
            vm.getWeatherDatabase()    //TODO: PROBLEM WITH MVVM
            vm.resultWeatherDatabase.observe(this@MainActivity) { weatherDatabase ->
                cityTextView.text = "${resources.getString(R.string.city)} ${weatherDatabase.city}"
                city = weatherDatabase.city
                degreesTextView.text = "${resources.getString(R.string.temperature)} ${weatherDatabase.degrees} ${resources.getString(R.string.metric)}"
                feelsLikeTextView.text = "${resources.getString(R.string.feelslike)} ${weatherDatabase.feelsLike} ${resources.getString(R.string.metric)}"
                weatherTextView.text = "${resources.getString(R.string.weather)} ${weatherDatabase.weather}"
                refreshButton.visibility = View.VISIBLE
            }
        }

        vm.resultWeatherRemote.observe(this@MainActivity) { weather ->
            if (weather.data != null) {
                sp.edit().putBoolean(open, true).apply()
                Log.e("TAG", "Success")
                Log.e("TAG", weather.data!!.city)
                Log.e("TAG", weather.data!!.weather)
                Log.e("TAG", weather.data!!.degrees.toString())
                Log.e("TAG", weather.data!!.feelsLike.toString())
                city = weather.data!!.city
                cityTextView.text = "${resources.getString(R.string.city)} ${weather.data!!.city}"
                degreesTextView.text = "${resources.getString(R.string.temperature)} ${weather.data!!.degrees} ${resources.getString(R.string.metric)}"
                feelsLikeTextView.text = "${resources.getString(R.string.feelslike)} ${weather.data!!.feelsLike} ${resources.getString(R.string.metric)}"
                weatherTextView.text = "${resources.getString(R.string.weather)} ${weather.data!!.weather}"
                loadingTextView.visibility = View.INVISIBLE
                refreshButton.visibility = View.VISIBLE
            } else {
                loadingTextView.text = weather.message
                cityTextView.text = ""
                degreesTextView.text = ""
                feelsLikeTextView.text = ""
                weatherTextView.text = ""
            }
        }


        buttonSetCity.setOnClickListener {
            loadingTextView.visibility = View.VISIBLE
            vm.getWeatherRemote(cityEditText.text.toString())
            vm.resultWeatherRemote.observe(this@MainActivity) { weather ->
                if (weather.data != null) {
                    vm.saveWeather(weather = weather.data!!)
                }
            }
        }

        refreshButton.setOnClickListener {
            loadingTextView.visibility = View.VISIBLE
            vm.getWeatherRemote(city)
            vm.resultWeatherRemote.observe(this@MainActivity) { weather ->
                if (weather.data != null) {
                    vm.updateWeather(weather = weather.data!!)
                }
            }
        }
    }
}
