package com.voleti.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.voleti.login.databinding.FragmentLoginBinding
import com.voleti.token_manager.TokenManager

class LoginFragment : Fragment(){

    private lateinit var loginBinding: FragmentLoginBinding
    private val tokenManager by lazy {
        TokenManager(requireContext())
    }

    private val loginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginBinding.loginViewModel = loginViewModel
        loginBinding.lifecycleOwner = this
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding.loginBtn.setOnClickListener {
            loginViewModel.login(tokenManager)
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToNavigationHome(loginViewModel.email.value))
        }
    }

}