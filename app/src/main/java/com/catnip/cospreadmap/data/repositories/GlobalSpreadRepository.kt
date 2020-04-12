package com.catnip.cospreadmap.data.repositories

import androidx.lifecycle.MutableLiveData
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cateringlist.utils.rx.AppScheduler
import com.catnip.cateringlist.utils.rx.addTo
import com.catnip.cateringlist.utils.rx.performOnBackOutOnMain
import com.catnip.cospreadmap.data.db.datasource.GlobalSpreadRoomDataSource
import com.catnip.cospreadmap.data.model.spread.global.GlobalDataWrapper
import com.catnip.cospreadmap.data.network.datasource.GlobalSpreadNetworkDataSource
import com.catnip.cospreadmap.utils.ext.error
import com.catnip.cospreadmap.utils.ext.loading
import com.catnip.cospreadmap.utils.ext.success
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

class GlobalSpreadRepository(
    private val networkDataSource: GlobalSpreadNetworkDataSource,
    private val dbDataSource: GlobalSpreadRoomDataSource,
    private val scheduler: AppScheduler,
    private val disposable: CompositeDisposable
) : BaseRepository {

    val result: MutableLiveData<ResultState<GlobalDataWrapper>> = MutableLiveData()

    override fun getData() {
        result.value = ResultState.loading(true)
        Observable.concat(dbDataSource.get(), fetchFromNetwork())
            .doOnError { it.printStackTrace() }
            .onErrorResumeNext(
                fetchFromNetwork()
            ).performOnBackOutOnMain(scheduler)
            .subscribe(
                {
                    result.value = ResultState.success(it)
                },
                {
                    result.value = ResultState.error(it)
                    it.printStackTrace()
                })
            .addTo(disposable)
    }

    private fun fetchFromNetwork(): Observable<GlobalDataWrapper> = networkDataSource.fetch()
        .doOnNext {
            dbDataSource.clear()
            dbDataSource.store(it)
        }

    override fun onCleared() {
        disposable.clear()
    }
}