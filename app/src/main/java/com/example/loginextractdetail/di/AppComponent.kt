package com.example.loginextractdetail.di

import org.koin.core.module.Module

object AppComponent {

    fun getAllModules() : List<Module> = listOf(*getModules())

    private fun getModules() : Array<Module> = arrayOf(modules)

}