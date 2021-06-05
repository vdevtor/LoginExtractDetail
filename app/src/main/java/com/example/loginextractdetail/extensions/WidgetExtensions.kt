package com.example.loginextractdetail.extensions

import android.content.Context
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.disMissError() {
    this.error = null
    this.isErrorEnabled = false
}

fun displayToast(s:String,context:Context?){
    Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
}