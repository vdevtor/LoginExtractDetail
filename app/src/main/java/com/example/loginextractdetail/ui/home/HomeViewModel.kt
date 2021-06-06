package com.example.loginextractdetail.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginextractdetail.base.BaseRepository
import com.example.loginextractdetail.base.BaseViewModel
import com.example.loginextractdetail.data.model.userextract.BillStatus
import com.example.loginextractdetail.data.response.GetResponseApi
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class HomeViewModel(val repository: BaseRepository) :BaseViewModel(),KoinComponent {

    val onResultUserDetails = MutableLiveData<BillStatus>()
    val onResultError = MutableLiveData<String>()


    fun getDetails(token: String){
        getUserDetailsFromApi(token)
    }

   private fun getUserDetailsFromApi(token:String){
       viewModelScope.launch {
           when(val response = repository.getUserDetails(token)){
               is GetResponseApi.ResponseSuccess ->{
                   onResultUserDetails.postValue(response.data as BillStatus)
               }
               is GetResponseApi.ResponseError ->{
                   onResultError.postValue(response.message)
               }
           }
       }
    }
}