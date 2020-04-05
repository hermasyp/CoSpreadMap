package com.catnip.cospreadmap.di

import androidx.room.Room
import com.catnip.cateringlist.utils.rx.AppScheduler
import com.catnip.cospreadmap.data.db.AppDatabase
import com.catnip.cospreadmap.data.network.RetrofitApi
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val networkModule = module {
    single { RetrofitApi() }
    single { AppScheduler() }
    factory { CompositeDisposable() }
}
val databaseModule = module{
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DBNAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { get<AppDatabase>().globalSpreadDao() }
    single { get<AppDatabase>().localSpreadDao() }
}

val viewModels = module {
    // viewmodels will written in  here
}

val repositories = module {
    // a repositories will written in  here
}
