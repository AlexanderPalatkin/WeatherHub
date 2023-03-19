package com.example.weatherhub.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.weatherhub.R
import com.example.weatherhub.databinding.FragmentDetailsBinding
import com.example.weatherhub.repository.*
import com.example.weatherhub.utils.*
import com.example.weatherhub.viewmodel.ResponseState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), OnServerResponse, OnServerResponseListener {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val receiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {intent ->
                intent.getParcelableExtra<WeatherDTO>(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER)?.let {
                    onResponse(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var currentCityName: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver, IntentFilter(
            KEY_WAVE_SERVICE_BROADCAST))
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            currentCityName = it.city.name
//            WeatherLoader(this@DetailsFragment, this@DetailsFragment).loadWeather(
//                it.city.lat,
//                it.city.lon
//            )
            requireActivity().startService(Intent(requireActivity(), DetailsService::class.java).apply {
                putExtra(KEY_BUNDLE_LAT, it.city.lat)
                putExtra(KEY_BUNDLE_LON, it.city.lon)
            })
        }
    }

    private fun renderData(weather: WeatherDTO) {
        with(binding) {
            loadingLayout.visibility = View.GONE
            cityName.text = currentCityName
            temperatureValue.text = weather.factDTO.temperature.toString()
            feelsLikeValue.text = weather.factDTO.feelsLike.toString()
            cityCoordinates.text = buildString {
                append(weather.infoDTO.lat)
                append(" ")
                append(weather.infoDTO.lon)
            }
//            mainView.showSnackBar(getString(R.string.its_work), Snackbar.LENGTH_SHORT)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    private fun View.showSnackBar(
        text: String,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).show()
    }

    override fun onResponse(weatherDTO: WeatherDTO) {
        renderData(weatherDTO)
    }

    override fun onResponseState(responseState: ResponseState) {
        when(responseState) {
            is ResponseState.ResponseOk -> mainView.showSnackBar("Ok", Snackbar.LENGTH_LONG)
            is ResponseState.ErrorClient -> mainView.showSnackBar("Ошибка $responseState на стороне клиента", Snackbar.LENGTH_LONG)
            is ResponseState.ErrorServer -> mainView.showSnackBar("Ошибка $responseState на стороне сервера", Snackbar.LENGTH_LONG)
        }
    }
}