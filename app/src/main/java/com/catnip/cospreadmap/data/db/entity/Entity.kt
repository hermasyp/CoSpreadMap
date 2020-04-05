package com.catnip.cospreadmap.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.catnip.cospreadmap.data.db.typeconverter.GlobalWrapperTypeConverter
import com.catnip.cospreadmap.data.db.typeconverter.LocalWrapperTypeConverter
import com.catnip.cospreadmap.data.model.spread.global.GlobalDataWrapper
import com.catnip.cospreadmap.data.model.spread.local.LocalDataWrapper

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

@Entity(tableName = "local_spread_data")
data class LocalSpreadEntity(
    @TypeConverters(LocalWrapperTypeConverter::class)
    var localDataWrapper: LocalDataWrapper
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Entity(tableName = "global_spread_data")
data class GlobalSpreadEntity(
    @TypeConverters(GlobalWrapperTypeConverter::class)
    var globalDataWrapper: GlobalDataWrapper
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


