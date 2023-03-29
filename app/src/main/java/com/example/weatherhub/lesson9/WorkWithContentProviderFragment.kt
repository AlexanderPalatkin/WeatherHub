package com.example.weatherhub.lesson9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherhub.R
import com.example.weatherhub.databinding.FragmentWorkWithContentProviderBinding

class WorkWithContentProviderFragment : Fragment() {

    private var _binding: FragmentWorkWithContentProviderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkWithContentProviderBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() =
            WorkWithContentProviderFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}