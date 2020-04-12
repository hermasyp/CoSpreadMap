package com.catnip.cospreadmap.data.network.datasource

import com.catnip.cospreadmap.data.model.province.Province
import com.catnip.cospreadmap.data.model.spread.local.LocalDataWrapper
import com.catnip.cospreadmap.data.model.spread.local.LocalSpreadResponse
import com.catnip.cospreadmap.data.model.spread.local.LocalSpreadWrapper
import com.catnip.cospreadmap.data.model.total.local.IndonesiaCount
import com.catnip.cospreadmap.data.network.RetrofitApi
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

class LocalSpreadNetworkDataSource(private val client: RetrofitApi) :
    NetworkDataSource<Observable<LocalDataWrapper>> {
    override fun fetch(): Observable<LocalDataWrapper> =
        //get two list of local spread and list of indonesian province
        Observable.zip(
            client.getLocalSpread(), client.getIndonesianProvince(),
            BiFunction<List<LocalSpreadResponse>, List<Province>,
                    Pair<List<LocalSpreadResponse>, List<Province>>> { t1, t2 ->
                Pair(t1, t2)
            }
        )
            .doOnError {
                it.printStackTrace()
            }
            //filtering and search local spread with their province data
            .flatMap { pair ->
                Observable.fromIterable(pair.first)
                    .flatMap { spread ->
                        Observable.zip(Observable.just(spread),
                            Observable.fromIterable(pair.second)
                                .filter { province -> province.id.equals(spread.localSpread?.provinceID) },
                            BiFunction<LocalSpreadResponse, Province, LocalSpreadWrapper> { t1, t2 ->
                                LocalSpreadWrapper(t1.localSpread, t2)
                            })
                    }
            }.toList().toObservable()
            .doOnError {
                it.printStackTrace()
            }
            //zip with local spread count
            .flatMap {
                Observable.zip(Observable.just(it),
                    client.getIndonesiaCount(),
                    BiFunction<List<LocalSpreadWrapper>, List<IndonesiaCount>, LocalDataWrapper> { t1, t2 ->
                        LocalDataWrapper(t1, t2[0])
                    })
            }

}