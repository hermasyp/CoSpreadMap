package com.catnip.cospreadmap.data.network.datasource

import com.catnip.cospreadmap.data.model.spread.global.GlobalDataWrapper
import com.catnip.cospreadmap.data.model.spread.global.GlobalSpreadResponse
import com.catnip.cospreadmap.data.model.total.global.Count
import com.catnip.cospreadmap.data.model.total.global.GlobalCountWrapper
import com.catnip.cospreadmap.data.network.RetrofitApi
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

class GlobalSpreadNetworkDataSource(private val client: RetrofitApi) :
    NetworkDataSource<Observable<GlobalDataWrapper>> {

    override fun fetch(): Observable<GlobalDataWrapper> = Observable.zip(
        client.getGlobalPositive(),
        client.getGlobalCured(),
        client.getGlobalDeath(), Function3<Count,Count,Count,GlobalCountWrapper>{
            pos,cur,death -> GlobalCountWrapper(pos,cur,death)
        }
    ).flatMap {
        Observable.zip(
            Observable.just(it),
            client.getGlobalSpread(),
            BiFunction<GlobalCountWrapper,List<GlobalSpreadResponse>,GlobalDataWrapper>{
                r1,r2 -> GlobalDataWrapper(r2,r1)
            }
        )
    }


}