package com.catnip.cospreadmap.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.catnip.cospreadmap.data.db.entity.GlobalSpreadEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Dao
interface GlobalSpreadDao {
    @Query("SELECT * FROM global_spread_data ORDER BY ID DESC LIMIT 1")
    fun getGlobalSpreadData(): Single<GlobalSpreadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGlobalSpreadData(data: GlobalSpreadEntity)

    @Query("DELETE FROM global_spread_data")
    fun deleteAllGlobalSpreadData()

}