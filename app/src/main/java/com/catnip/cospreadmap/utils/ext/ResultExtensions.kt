package com.catnip.cospreadmap.utils.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.catnip.cateringlist.utils.result.ResultState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val data = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe { t: T -> data.value = t })
    return data
}

fun <T> PublishSubject<ResultState<T>>.failed(msg: String) {
    with(this) {
        loading(false)
        onNext(ResultState.failure(msg))
    }
}

fun <T> PublishSubject<ResultState<T>>.error(e: Throwable) {
    with(this) {
        loading(false)
        onNext(ResultState.error(e))
    }
}

fun <T> PublishSubject<ResultState<T>>.success(t: T) {
    with(this) {
        loading(false)
        onNext(ResultState.success(t))
    }
}

fun <T> PublishSubject<ResultState<T>>.loading(isLoading: Boolean) {
    this.onNext(ResultState.loading(isLoading))
}