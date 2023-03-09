package com.example.weatherhub.view.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherhub.R
import com.example.weatherhub.databinding.FragmentWeatherListBinding
import com.example.weatherhub.viewmodel.AppState
import com.example.weatherhub.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherListFragment : Fragment() {

    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding!!

    private val adapter = WeatherListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var isRussian: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        val observer = Observer<Any>{ renderData(it)}

        val observer = object : Observer<AppState> {
            override fun onChanged(data: AppState) {
                renderData(data)
            }
        }

        viewModel.getData().observe(viewLifecycleOwner, observer)
        binding.floatingActionButton.setOnClickListener {
            isRussian = !isRussian
            if (isRussian){
                viewModel.getWeatherRussian()
                binding.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_russia))
            } else {
                viewModel.getWeatherWorld()
                binding.floatingActionButton.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_earth))
            }
        }
        viewModel.getWeatherRussian()
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.root, "Не получилось", Snackbar.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)


//                binding.cityName.text = data.weatherData.city.name
//                binding.temperatureValue.text = data.weatherData.temperature.toString()
//                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
//                binding.cityCoordinates.text = buildString {
//        append(data.weatherData.city.lat)
//        append(" ")
//        append(data.weatherData.city.lon)
//    }
//                Snackbar.make(binding.mainView, "Получилось", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}