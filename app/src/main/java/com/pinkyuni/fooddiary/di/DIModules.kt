package com.pinkyuni.fooddiary.di

import com.pinkyuni.fooddiary.ui.MainViewModel
import com.pinkyuni.fooddiary.data.IRepository
import com.pinkyuni.fooddiary.data.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diModules = module {
    viewModel { MainViewModel(get()) }
    single<IRepository> { Repository(androidContext()) }
}