package com.pinkyuni.fooddiary.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface DisposeHolder {
    fun Disposable.unsubscribeOnDestroy()
    fun clearSubscriptions()
}

class DisposeHolderImpl : DisposeHolder {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun Disposable.unsubscribeOnDestroy() {
        compositeDisposable.add(this)
    }

    override fun clearSubscriptions() {
        compositeDisposable.dispose()
    }
}
