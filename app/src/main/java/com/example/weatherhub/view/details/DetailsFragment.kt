package com.example.weatherhub.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherhub.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

//    private fun renderData(data: AppState) {
//        when (data) {
//            is AppState.Error -> {
//                binding.loadingLayout.visibility = View.GONE
//                Snackbar.make(binding.mainView, "Не получилось", Snackbar.LENGTH_SHORT).show()
//            }
//            is AppState.Loading -> {
//                binding.loadingLayout.visibility = View.VISIBLE
//            }
//            is AppState.Success -> {
//                binding.loadingLayout.visibility = View.GONE
//                binding.cityName.text = data.weatherData.city.name
//                binding.temperatureValue.text = data.weatherData.temperature.toString()
//                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
//                binding.cityCoordinates.text = buildString {
//        append(data.weatherData.city.lat)
//        append(" ")
//        append(data.weatherData.city.lon)
//    }
//                Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_SHORT).show()
//            }
//        }
//    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}