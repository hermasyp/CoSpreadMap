package com.catnip.cospreadmap.data.db.datasource

import com.catnip.cateringlist.utils.rx.AppScheduler
import com.catnip.cospreadmap.data.db.dao.LocalSpreadDao
import com.catnip.cospreadmap.data.db.entity.LocalSpreadEntity
import com.catnip.cospreadmap.data.model.spread.local.LocalDataWrapper
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.concurrent.ExecutorService

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LocalSpreadRoomDataSource(
    private val dao: LocalSpreadDao,
    private val executorService: ExecutorService
) : RoomDataSource<LocalDataWrapper> {


    override fun get(): Observable<LocalDataWrapper> = dao.getLocalSpreadData()
        .map { it.localDataWrapper }.toObservable()

    override fun store(data: LocalDataWrapper) {
        executorService.execute {
            dao.insertLocalSpreadData(
                LocalSpreadEntity(data)
            )
        }
    }

    override fun clear() {
        executorService.execute {
            dao.deleteAllLocalSpreadData()
        }
    }
}

