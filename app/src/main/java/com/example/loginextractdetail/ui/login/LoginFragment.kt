package com.example.loginextractdetail.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.loginextractdetail.R
import com.example.loginextractdetail.databinding.FragmentLoginBinding
import com.example.loginextractdetail.extensions.disMissError
import com.example.loginextractdetail.extensions.displayToast
import com.example.loginextractdetail.utils.Constants.ACCESS_DENIED
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        textViewChangeListeners()
        buttonsListeners()

    }

    private fun buttonsListeners() {

        binding?.btnLogin?.setOnClickListener {
            val login = binding?.etLogin?.text.toString()
            val password = binding?.etPassWord?.text.toString()
            loginViewModel.authentication(login, password)
            setObservables()
        }
    }


    private fun textViewChangeListeners() {

        binding?.etLogin?.addTextChangedListener {
            binding?.tilLogin?.disMissError()
        }
        binding?.etPassWord?.addTextChangedListener {
            binding?.tilPassWord?.disMissError()
        }
    }

    private fun setObservables() {
        loginViewModel.authenticationStateEvent.observe(viewLifecycleOwner, { authenticationState ->
            when (authenticationState) {
                is LoginViewModel.AuthenticationState.Authenticated -> {
                    loginViewModel.onResultLogin.observe(viewLifecycleOwner, { user ->
                        loginViewModel.authenticateToken(user.data?.token ?: getString(
                                R.string.login_denied_access),
                                loginViewModel.userLogin)
                        loginViewModel.token.observe(viewLifecycleOwner, { token ->
                            displayToast(token, this.context)

                        })


                    })

                }
                is LoginViewModel.AuthenticationState.InvalidAuthentication -> {
                    val validationFields: Map<String, TextInputLayout?> = initValidationFields()
                    authenticationState.fields.forEach { fieldsError ->
                        validationFields[fieldsError.first]?.apply {
                            error = getString(fieldsError.second)

                        }
                    }
                }
                else -> return@observe
            }

        })
    }

    private fun checkToken() {
        val token = loginViewModel.token.value
        if (token != null && token != ACCESS_DENIED) {
            displayToast(token, this.context)
        }
    }

    private fun initValidationFields() = mapOf(
            LoginViewModel.INPUT_USERNAME.first to binding?.tilLogin,
            LoginViewModel.INPUT_PASSWORD.first to binding?.tilPassWord
    )
}