package com.catnip.cospreadmap.data.db.datasource

import com.catnip.cateringlist.utils.rx.AppScheduler
import com.catnip.cospreadmap.data.db.dao.GlobalSpreadDao
import com.catnip.cospreadmap.data.db.entity.GlobalSpreadEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class GlobalSpreadRoomDataSource(
    private val dao: GlobalSpreadDao
) : RoomDataSource<GlobalSpreadEntity> {
    override fun get(): Observable<GlobalSpreadEntity> = dao.getGlobalSpreadData()

    override fun store(data: GlobalSpreadEntity): Completable = dao.insertGlobalSpreadData(data)

    override fun clear(): Completable = dao.deleteAllGlobalSpreadData()
}