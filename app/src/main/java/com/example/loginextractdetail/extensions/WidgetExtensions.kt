package com.example.loginextractdetail.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.loginextractdetail.GlideApp
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.disMissError() {
    this.error = null
    this.isErrorEnabled = false
}

fun ImageView.load(url: String?) {
    GlideApp.with(context)
            .load(url)
            .into(this)
}

fun displayToast(s: String, context: Context?) {
    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}

fun dialogBuilderLogin(context: Context?) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage("Verifique os campos")
            .setPositiveButton("Tentar Novamente", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
    val alert = dialogBuilder.create()
    alert.setTitle("Falha ao Realizar Login")
    alert.show()
}