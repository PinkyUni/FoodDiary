package com.pinkyuni.fooddiary.di

import com.pinkyuni.fooddiary.ui.MainViewModel
import com.pinkyuni.fooddiary.data.IRepository
import com.pinkyuni.fooddiary.data.Repository
import com.pinkyuni.fooddiary.utils.DisposeHolder
import com.pinkyuni.fooddiary.utils.DisposeHolderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diModules = module {
    single<DisposeHolder> { DisposeHolderImpl() }
    viewModel { MainViewModel(get(), get()) }
    single<IRepository> { Repository(androidContext()) }
}