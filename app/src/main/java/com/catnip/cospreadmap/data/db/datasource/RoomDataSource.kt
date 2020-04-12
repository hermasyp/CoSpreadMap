package com.catnip.cospreadmap.data.db.datasource

import io.reactivex.Completable
import io.reactivex.Observable


/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface RoomDataSource<T> {
    fun get(): Observable<T>
    fun store(data: T)
    fun clear()
}