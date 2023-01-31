package com.voleti.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.voleti.details.DetailsFragmentDirections
import com.voleti.home.databinding.FragmentHomeBinding
import com.voleti.navigation.MobileNavigationDirections

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonHome.setOnClickListener {
            val action = MobileNavigationDirections.actionToDetailsFragment("Hello from Home Fragment")
            Navigation.findNavController(it).navigate(action)
        }
    }

}