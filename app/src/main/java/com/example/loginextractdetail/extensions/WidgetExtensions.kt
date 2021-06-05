package com.example.loginextractdetail.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.disMissError() {
    this.error = null
    this.isErrorEnabled = false
}

fun displayToast(s:String,context:Context?){
    Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
}

fun dialogBuilderLogin(context: Context?){
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage("Deseja ...?")
            .setPositiveButton("Tentar Novamente",DialogInterface.OnClickListener { dialog,_ ->
                dialog.cancel()
            })
    val alert = dialogBuilder.create()
    alert.setTitle("Falha ao Realizar Login")
    alert.show()
}