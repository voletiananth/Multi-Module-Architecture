package com.voleti.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voleti.token_manager.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    val email = MutableStateFlow("")

    val isEmailValid:StateFlow<Boolean> by lazy {
        email.map {
            it.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(it).matches() }.stateIn(viewModelScope,
            SharingStarted.Eagerly, false)
    }

    fun login(tokenManager: TokenManager) {
        tokenManager.saveToken(email.value)
    }
}