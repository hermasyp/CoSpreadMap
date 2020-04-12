package com.catnip.cospreadmap.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.catnip.cospreadmap.data.db.dao.GlobalSpreadDao
import com.catnip.cospreadmap.data.db.dao.LocalSpreadDao
import com.catnip.cospreadmap.data.db.entity.GlobalSpreadEntity
import com.catnip.cospreadmap.data.db.entity.LocalSpreadEntity
import com.catnip.cospreadmap.data.db.typeconverter.GlobalWrapperTypeConverter
import com.catnip.cospreadmap.data.db.typeconverter.LocalWrapperTypeConverter

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/



@Database(entities = [GlobalSpreadEntity::class,LocalSpreadEntity::class],version = 1)
@TypeConverters(LocalWrapperTypeConverter::class,GlobalWrapperTypeConverter::class)
abstract class AppDatabase : RoomDatabase(){
companion object{const val DBNAME = "CoSpreadMapDB"}
    abstract fun localSpreadDao():LocalSpreadDao
    abstract fun globalSpreadDao():GlobalSpreadDao
}