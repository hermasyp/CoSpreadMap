package com.catnip.cospreadmap.data.repositories

import androidx.lifecycle.MutableLiveData
import com.catnip.cateringlist.utils.result.ResultState
import com.catnip.cospreadmap.utils.rx.AppScheduler
import com.catnip.cateringlist.utils.rx.addTo
import com.catnip.cateringlist.utils.rx.performOnBackOutOnMain
import com.catnip.cospreadmap.data.db.datasource.LocalSpreadRoomDataSource
import com.catnip.cospreadmap.data.model.spread.local.LocalDataWrapper
import com.catnip.cospreadmap.data.network.datasource.LocalSpreadNetworkDataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LocalSpreadRepository(
    private val networkDataSource: LocalSpreadNetworkDataSource,
    private val dbDataSource: LocalSpreadRoomDataSource,
    private val scheduler: AppScheduler,
    private val disposable: CompositeDisposable
) : BaseRepository {

    val result: MutableLiveData<ResultState<LocalDataWrapper>> = MutableLiveData()

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


    private fun fetchFromNetwork(): Observable<LocalDataWrapper> = networkDataSource.fetch()
        .doAfterNext{
            dbDataSource.clear()
            dbDataSource.store(it)
        }


    override fun onCleared() {
        disposable.clear()
    }


}