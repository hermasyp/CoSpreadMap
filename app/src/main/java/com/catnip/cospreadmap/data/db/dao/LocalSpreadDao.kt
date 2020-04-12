package com.catnip.cospreadmap.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.catnip.cospreadmap.data.db.entity.LocalSpreadEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Dao
interface LocalSpreadDao {
    @Query("SELECT * FROM local_spread_data ORDER BY ID DESC LIMIT 1")
    fun getLocalSpreadData(): Single<LocalSpreadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocalSpreadData(data: LocalSpreadEntity)

    @Query("DELETE FROM local_spread_data")
    fun deleteAllLocalSpreadData()
}