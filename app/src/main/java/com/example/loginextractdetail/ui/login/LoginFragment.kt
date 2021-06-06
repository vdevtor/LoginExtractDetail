package com.example.loginextractdetail.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.loginextractdetail.databinding.FragmentLoginBinding
import com.example.loginextractdetail.extensions.dialogBuilderLogin
import com.example.loginextractdetail.extensions.disMissError
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.concurrent.schedule

class LoginFragment : Fragment() {
    private var token: String? = null
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
            loginViewModel.authenticateRequestApi(login, password)
            checkAuthenticationState()
            Timer().schedule(2000) {

                lifecycleScope.launch {
                    withContext(Dispatchers.Main) {
                        setObservables()
                        if (checkToken()) {
                            goToHome()
                        } else {
                            dialogBuilderLogin(context)
                        }
                    }
                }

            }
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

    private fun checkAuthenticationState() {
        loginViewModel.authenticationStateEvent.observe(viewLifecycleOwner, { authenticationState ->
            when (authenticationState) {

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

    private fun setObservables() {
        loginViewModel.onResultLogin.observe(viewLifecycleOwner, {
            loginViewModel.authenticateToken(it.data?.token)
        })
    }

    private fun checkToken(): Boolean {
        token = loginViewModel.token.value
        return token?.isNotEmpty() ?: false
    }

    private fun goToHome() {
        val directions = LoginFragmentDirections.actionLoginFragmentToHomeFragment(token
                ?: "Null")
        findNavController().navigate(directions)
    }

    private fun initValidationFields() = mapOf(
            LoginViewModel.INPUT_USERNAME.first to binding?.tilLogin,
            LoginViewModel.INPUT_PASSWORD.first to binding?.tilPassWord
    )
}