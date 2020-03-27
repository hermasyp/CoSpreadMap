package com.catnip.cospreadmap.di


import com.catnip.cospreadmap.data.network.RetrofitApi
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val networkModule = module {
    single { RetrofitApi() }
}

val viewModels = module {
    // viewmodels will written in  here
}

val repositories = module {
    // a repositories will written in  here
}
