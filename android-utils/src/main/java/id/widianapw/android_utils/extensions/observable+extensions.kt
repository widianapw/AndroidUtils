package id.widianapw.android_utils.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

fun <T> Observable<T>.doSubscribe(observer: DisposableObserver<in T>): Disposable {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(observer)
}

fun Disposable.addDisposable(disposable: CompositeDisposable) {
    disposable.add(this)
}

fun <T> Observable<T>.doSubscribeWithDisposable(
    observer: DisposableObserver<in T>,
    disposable: CompositeDisposable
) {
    this.doSubscribe(observer).addDisposable(disposable)
}