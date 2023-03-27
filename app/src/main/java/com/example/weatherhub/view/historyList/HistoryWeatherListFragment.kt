package com.example.weatherhub.view.historyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherhub.databinding.FragmentHistoryWeatherListBinding
import com.example.weatherhub.viewmodel.AppState
import com.example.weatherhub.viewmodel.HistoryViewModel
import com.example.weatherhub.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class HistoryWeatherListFragment : Fragment() {

    private var _binding: FragmentHistoryWeatherListBinding? = null
    private val binding get() = _binding!!

    private val adapter = HistoryWeatherListAdapter()

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this)[HistoryViewModel::class.java]
    }

    private var isRussian: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        val observer = Observer<AppState> { data -> renderData(data) }
        viewModel.getData().observe(viewLifecycleOwner, observer)

        viewModel.getAll()
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
//                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(
                    binding.root,
                    "Не удалось получить список городов",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is AppState.Loading -> {
//                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
//                binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherList)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryWeatherListFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}