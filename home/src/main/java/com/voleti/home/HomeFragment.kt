package com.voleti.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.voleti.home.databinding.FragmentHomeBinding
import com.voleti.navigation.MobileNavigationDirections
import com.voleti.token_manager.TokenManager

class HomeFragment : Fragment() {

    private val args: HomeFragmentArgs by navArgs()

    private val tokenManager: TokenManager by lazy {
        TokenManager(requireContext())
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.args = args
        binding.tokenManager = tokenManager
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonHome.setOnClickListener {
            val action = MobileNavigationDirections.actionToDetailsFragment("Hello from Home Fragment")
            Navigation.findNavController(it).navigate(action)
        }

        binding.signOutButton.setOnClickListener {
            tokenManager.deleteToken()
            findNavController().navigate(MobileNavigationDirections.actionToLoginFragment())
        }

    }

}