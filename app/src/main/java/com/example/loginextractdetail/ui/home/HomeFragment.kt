package com.example.loginextractdetail.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginextractdetail.R
import com.example.loginextractdetail.data.model.userextract.Installment
import com.example.loginextractdetail.databinding.FragmentHomeBinding
import com.example.loginextractdetail.ui.login.LoginViewModel
import com.example.loginextractdetail.utils.ClickContract
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), ClickContract {
    private val args: HomeFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private val loginViewModel: LoginViewModel by sharedViewModel()
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        registerDeviceBackStack()
        initUserData()
        setObservables()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        cancelRegistration()
        return super.onOptionsItemSelected(item)
    }

    private fun registerDeviceBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelRegistration()
        }
    }

    private fun cancelRegistration() {
        loginViewModel.refuseAuthentication()
        findNavController().popBackStack(R.id.loginFragment, false)
    }

    private fun initUserData() {
        homeViewModel.getDetails(args.token)
    }

    private fun setObservables() {
        homeViewModel.onResultUserDetails.observe(viewLifecycleOwner, { userDetails ->
            binding?.apply {
                userName.text = userDetails.data.name
                avaiableBalanceValue.text = userDetails.data.limits.available
                balanceLimitValue.text = userDetails.data.limits.total
                balanceLimitUsedValue.text = userDetails.data.limits.expent
            }
            userDetails.data.installments?.let { setupRecycler(it) }
        })
    }

    private fun setupRecycler(installment: List<Installment>) {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(installment, this@HomeFragment)
        }
    }

    override fun onClick(result: Installment) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(result)
        findNavController().navigate(directions)
    }
}