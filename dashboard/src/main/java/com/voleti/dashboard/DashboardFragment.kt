package com.voleti.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.voleti.dashboard.databinding.FragmentDashboardBinding
import com.voleti.navigation.MobileNavigationDirections

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDashboard.setOnClickListener {
            val action = MobileNavigationDirections.actionToDetailsFragment("Hello from Dashboard Fragment")
            Navigation.findNavController(it).navigate(action)
        }
    }
}