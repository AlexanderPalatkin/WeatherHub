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
import com.example.weatherhub.repository.Weather
import com.example.weatherhub.utils.KEY_BUNDLE_WEATHER
import com.example.weatherhub.view.details.DetailsFragment
import com.example.weatherhub.viewmodel.AppState
import com.example.weatherhub.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherListFragment : Fragment(), OnItemListClickListener {

    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding!!

    private val adapter = WeatherListAdapter(this)

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

        initRecyclerView()
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

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

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_BUNDLE_WEATHER, weather)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, DetailsFragment.newInstance(bundle)).addToBackStack("").commit()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }
}