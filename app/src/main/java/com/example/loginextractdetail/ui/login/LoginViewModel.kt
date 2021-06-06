package com.example.loginextractdetail.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginextractdetail.R
import com.example.loginextractdetail.base.BaseRepository
import com.example.loginextractdetail.data.model.userauth.User
import com.example.loginextractdetail.data.response.GetResponseApi
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class LoginViewModel(val repository: BaseRepository) : ViewModel(), KoinComponent {

    sealed class AuthenticationState {
        object Authenticated : AuthenticationState()
        object UnAuthenticated : AuthenticationState()
        class InvalidAuthentication(val fields: List<Pair<String, Int>>) : AuthenticationState()
    }

    var onResultLogin = MutableLiveData<User>()
    var onResultLoginError = MutableLiveData<String>()
    var userLogin: String = ""
    var userPassword: String = ""
    var token = MutableLiveData<String>()
    private val _authenticationStateEvent = MutableLiveData<AuthenticationState>()

    val authenticationStateEvent: LiveData<AuthenticationState>
        get() = _authenticationStateEvent


    fun authenticateToken(token: String?) {
        this.token.value = token
        if (this.token.value.isNullOrEmpty()) {
            _authenticationStateEvent.postValue(AuthenticationState.UnAuthenticated)
        } else {
            _authenticationStateEvent.value = AuthenticationState.Authenticated
        }
    }

    private fun authentication(username: String, password: String) {
        this.userLogin = username
        this.userPassword = password
        _authenticationStateEvent.postValue(AuthenticationState.Authenticated)
    }

    private fun isValidForm(userlogin: String, password: String): Boolean {
        val invalidFields = arrayListOf<Pair<String, Int>>()

        if (userlogin.isEmpty() || userlogin != "parkour!") {
            invalidFields.add(INPUT_USERNAME)
        }

        if (password.isEmpty() || password != "123456") {
            invalidFields.add(INPUT_PASSWORD)
        }

        if (invalidFields.isNotEmpty()) {
            _authenticationStateEvent.value = AuthenticationState.InvalidAuthentication(invalidFields)
            return false
        }
        return true
    }

    fun authenticateRequestApi(user: String, password: String) {
        viewModelScope.launch {
            when (val response = repository.authenticateUser(user, password)) {
                is GetResponseApi.ResponseSuccess -> {
                    onResultLogin.postValue(response.data as User)
                    if (isValidForm(user, password)) {
                        _authenticationStateEvent.postValue(AuthenticationState.Authenticated)
                        authentication(user, password)
                    }
                }
                is GetResponseApi.ResponseError -> {
                    onResultLoginError.postValue(response.message)
                }
            }
        }
    }

    fun refuseAuthentication() {
        _authenticationStateEvent.value = AuthenticationState.UnAuthenticated
        this.token.postValue("")
    }

    companion object {
        val INPUT_USERNAME = "INPUT_USERNAME" to R.string.login_input_layout_error_invalid_username
        val INPUT_PASSWORD = "INPUT_PASSWORD" to R.string.login_input_layout_error_invalid_password
    }
}