package com.example.loginextractdetail.extensions

import android.app.AlertDialog
import android.content.Context
import android.widget.ImageView
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

fun dialogBuilderLogin(context: Context?) {
    val dialogBuilder = AlertDialog.Builder(context)
    dialogBuilder.setMessage("Verifique os campos")
            .setPositiveButton("Tentar Novamente") { dialog, _ ->
                dialog.cancel()
            }
    val alert = dialogBuilder.create()
    alert.setTitle("Falha ao Realizar Login")
    alert.show()
}