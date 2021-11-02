package com.philabnb.job_test

import com.philabnb.job_test.Base.Navigator
import com.philabnb.job_test.Base.toDataPage
import com.philabnb.job_test.Base.toWebViewPage
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            get<Navigator>()::toDataPage,
            get<Navigator>()::toWebViewPage,
        )
    }
}