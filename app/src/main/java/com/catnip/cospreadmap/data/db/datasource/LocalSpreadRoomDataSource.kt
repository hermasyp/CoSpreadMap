package com.catnip.cospreadmap.data.db.datasource

import com.catnip.cateringlist.utils.rx.AppScheduler
import com.catnip.cospreadmap.data.db.dao.LocalSpreadDao
import com.catnip.cospreadmap.data.db.entity.LocalSpreadEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class LocalSpreadRoomDataSource(
    private val dao: LocalSpreadDao) : RoomDataSource<LocalSpreadEntity> {
    override fun get(): Observable<LocalSpreadEntity> = dao.getLocalSpreadData()

    override fun store(data: LocalSpreadEntity): Completable = dao.insertLocalSpreadData(data)

    override fun clear(): Completable = dao.deleteAllLocalSpreadData()
}