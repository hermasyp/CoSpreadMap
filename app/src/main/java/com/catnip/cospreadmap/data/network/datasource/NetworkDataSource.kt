package com.catnip.cospreadmap.data.network.datasource

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface NetworkDataSource<T> {
    fun fetch() : T
}