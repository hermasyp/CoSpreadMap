package com.catnip.cospreadmap.data.db.datasource

import com.catnip.cospreadmap.data.db.dao.GlobalSpreadDao
import com.catnip.cospreadmap.data.db.entity.GlobalSpreadEntity
import com.catnip.cospreadmap.data.model.spread.global.GlobalDataWrapper
import io.reactivex.Observable
import java.util.concurrent.ExecutorService

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class GlobalSpreadRoomDataSource(
    private val dao: GlobalSpreadDao,
    private val executorService: ExecutorService
) : RoomDataSource<GlobalDataWrapper> {
    override fun get(): Observable<GlobalDataWrapper> = dao.getGlobalSpreadData()
        .map { it.globalDataWrapper }.toObservable()

    override fun store(data: GlobalDataWrapper) {
        executorService.execute {
            dao.insertGlobalSpreadData(
                GlobalSpreadEntity(data)
            )
        }
    }

    override fun clear() {
        executorService.execute {
            dao.deleteAllGlobalSpreadData()
        }
    }
}
